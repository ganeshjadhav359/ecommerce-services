package com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.validators;

import com.ganeshjadhav359.ecommercemicroservices.ecommauthservice.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword,Object> {
    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(final Object obj, ConstraintValidatorContext constraintValidatorContext) {
        final UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
