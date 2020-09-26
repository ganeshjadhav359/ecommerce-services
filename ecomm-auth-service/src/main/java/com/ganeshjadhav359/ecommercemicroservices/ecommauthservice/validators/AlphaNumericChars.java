package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = AlphaNumericCharsValidator.class)
@Documented
public @interface AlphaNumericChars {
    String message() default "Only use a-z/A-z/0-9";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
