package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Documented
public @interface ConfirmPassword {
    String message() default "password confirmation failed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
