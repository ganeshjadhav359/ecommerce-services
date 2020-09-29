package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;

import org.passay.*;

import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String > {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password==null)
            return false;
            PasswordValidator validator = new PasswordValidator(Arrays.asList(
                    new LengthRule(8,30),
                    new WhitespaceRule()
            ));
            RuleResult  result = validator.validate(new PasswordData(password));
            if(result.isValid())
                return true;

            List<String> messages = validator.getMessages(result);
            String  messageTemplate = String.join(",",messages);
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation();

            return false;
    }
}
