package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlacaValidation implements ConstraintValidator<Placa, String>{


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value == "^[A-Z]{3}[0-9][A-Z][0-9]{2}$")
      
    }
}