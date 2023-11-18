package com.shoppinglist.shoppinglist.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    @Override
    public void initialize(NullOrNotBlank value) {}
    @Override
    public boolean isValid(String value, ConstraintValidatorContext cxt) {
        return value == null || !value.trim().isEmpty();
    }
}
