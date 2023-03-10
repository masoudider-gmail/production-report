package ir.novinp.productionreport.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * alaki
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckStringLengthValidator.class)
public @interface CheckStringLength {

    String message() default "time is not matching!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
