package com.jinternal.employee.validators;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class GenderValueValidator implements ConstraintValidator<Gender, String> {

    @Override
    public void initialize(Gender constraintAnnotation) {
        // Need to be override
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }

        boolean isValid =
                object.equalsIgnoreCase(com.jinternal.employee.entities.Gender.FEMALE.toString())
                || object.equalsIgnoreCase(com.jinternal.employee.entities.Gender.NA.toString())
                        || object.equalsIgnoreCase(com.jinternal.employee.entities.Gender.MALE.toString());

        return isValid;
    }

}
