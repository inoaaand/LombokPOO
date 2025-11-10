package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AnoFabricacaoValidation implements ConstraintValidator<AnoFabricacao, Integer> {

    @Override
    public boolean isValid(Integer ano, ConstraintValidatorContext context) {
        if (ano == null) return false;
        int anoAtual = LocalDate.now().getYear();
        return ano >= 1886 && ano <= anoAtual;
    }
}
