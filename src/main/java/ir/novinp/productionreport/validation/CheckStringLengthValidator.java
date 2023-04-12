package ir.novinp.productionreport.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckStringLengthValidator implements ConstraintValidator<CheckStringLength, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return s.length() > 2 ? true : false;
    }

    @Override
    public void initialize(CheckStringLength constraintAnnotation) {

    }
}
