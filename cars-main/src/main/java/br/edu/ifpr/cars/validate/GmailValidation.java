package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailValidation implements ConstraintValidator<Gmail, String> {


    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isBlank()) {
            return false; // ou true se você quiser deixar NotBlank cuidar disso
        }
        return email.toLowerCase().endsWith("@gmail.com");
    }
}
