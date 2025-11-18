package com.texnoera.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }

        if (!Character.isUpperCase(value.charAt(0))) {
            return false;
        }

        if (value.length() < 8) {
            return false;
        }

        if (!value.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }

}
