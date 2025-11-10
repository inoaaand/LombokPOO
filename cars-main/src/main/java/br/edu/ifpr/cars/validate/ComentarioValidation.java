package br.edu.ifpr.cars.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ComentarioValidation implements ConstraintValidator<Comentario, String> {

    private static final List<String> palavrasProibidas = Arrays.asList(
        "burro", "idiota", "lixo", "inútil", "boba", "bobão", "paspalhão", "boboca"
    );

    @Override
    public boolean isValid(String comentario, ConstraintValidatorContext context) {
        if (comentario == null || comentario.isEmpty()) {
            return true; 
        }

        comentario = comentario.toLowerCase(); 

        
        for (String palavra : palavrasProibidas) {
            if (comentario.contains(palavra)) {
                return false; 
            }
        }

        return true; 
    }
}
