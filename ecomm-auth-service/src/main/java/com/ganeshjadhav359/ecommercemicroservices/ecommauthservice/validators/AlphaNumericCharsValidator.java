package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaNumericCharsValidator implements ConstraintValidator<AlphaNumericChars,String> {
    private static final String ALPHA_NUMERIC_PATTERN = "^[A-Za-z0-9]+$";

    @Override
    public void initialize(final AlphaNumericChars constraintAnnotation) {
    }

    @Override
    public boolean isValid(final @NotNull String username, final ConstraintValidatorContext context) {
        if(username==null)
            return false;
        return (validateString(username));
    }

    private boolean validateString(final String username) {
            Pattern pattern = Pattern.compile(ALPHA_NUMERIC_PATTERN);
            Matcher matcher = pattern.matcher(username);
            return matcher.matches();
    }

}
