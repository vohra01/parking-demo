package com.jinternal.employee.controllers.advices;

import com.jinternal.employee.dto.exception.Error;
import com.jinternal.employee.dto.exception.ErrorBuilder;
import com.jinternal.employee.dto.exception.ValidationError;
import com.jinternal.employee.exception.RestException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.valueOf;

@ControllerAdvice(annotations = RestController.class)
public class RestErrorAdvice {

    private Logger logger = getLogger(RestErrorAdvice.class);

    private MessageSource messageSource;

    @Autowired
    public RestErrorAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, BindException.class})
    @ResponseBody
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        logger.error(ex.getMessage(), ex);

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity(processFieldErrors(fieldErrors), BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity<Error> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception) {
        logger.error(exception.getMessage(), exception);

        Error error = new ErrorBuilder()
                .setMessage(resolveMessage(exception.getMessage(), emptyArray()))
                .setCode(resolveCode(exception.getMessage(), emptyArray()))
                .setHttpStatus(BAD_REQUEST)
                .build();
        return new ResponseEntity<Error>(error, error.getHttpStatus());

    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<Error> handleMethodArgumentTypeMismatchException(HttpServletRequest request,
                                                                           MethodArgumentTypeMismatchException exception) {
        logger.error(exception.getMessage(), exception);

        Error error = new ErrorBuilder()
                .setMessage(resolveMessage(exception.getMessage(), emptyArray()))
                .setCode(resolveCode(exception.getMessage(), emptyArray()))
                .setHttpStatus(BAD_REQUEST)
                .build();

        return new ResponseEntity<Error>(error, error.getHttpStatus());

    }

    @ExceptionHandler({RestException.class})
    @ResponseBody
    public ResponseEntity<Error> handleRestException(HttpServletRequest req, RestException ex) {

        logger.error(ex.getMessage(), ex);

        Error errorDTO = new ErrorBuilder()
                .setMessage(resolveMessage(ex.getMessage(), ex.getMessageArgs()))
                .setCode(resolveCode(ex.getMessage(), emptyArray()))
                .setHttpStatus(resolveHttpStatus(ex.getMessage(), emptyArray()))
                .build();
        return new ResponseEntity<Error>(errorDTO, errorDTO.getHttpStatus());
    }

    private Object[] emptyArray() {
        return new Object[0];
    }

    private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
        ValidationError validationError = new ValidationError();

        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveMessage(fieldError);
            validationError.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return validationError;
    }

    private String resolveMessage(String key, Object[] values) {
        String message = resolveMessage(key, "msg", values);
        return message == null ? key : message;
    }

    private String resolveCode(String key, Object[] values) {
        String code = resolveMessage(key, "code", values);
        return code == null ? "0000" : code;
    }

    private HttpStatus resolveHttpStatus(String key, Object[] values) {
        String http_status = resolveMessage(key, "http_code", values);
        return http_status == null ? BAD_REQUEST : valueOf(parseInt(http_status));
    }

    private String resolveMessage(String key, String subKey, Object[] values) {
        return messageSource.getMessage(key + "." + subKey, values, getLocale());
    }

    private String resolveMessage(FieldError fieldError) {
        String localizedMessage = messageSource.getMessage(fieldError, getLocale());

        if (localizedMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedMessage = fieldErrorCodes[0];
        }

        return localizedMessage;
    }

}
