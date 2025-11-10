package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CNHValidation implements ConstraintValidator<CNH, String> {

    @Override
    public boolean isValid(String cnh, ConstraintValidatorContext context) {
        if (cnh == null || cnh.isEmpty()) return false;
        return cnh.matches("\\d{11}");
    }
}
