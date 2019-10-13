package com.jinternal.employee.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValueValidator.class)
@Documented
public @interface Gender {

    String message() default "{com.jinternal.employee.validators.Gender.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

