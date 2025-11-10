package br.edu.ifpr.cars.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AnoFabricacaoValidation.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnoFabricacao {
    String message() default "Ano de fabricação inválido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

