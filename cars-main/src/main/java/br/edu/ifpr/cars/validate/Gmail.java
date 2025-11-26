package br.edu.ifpr.cars.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GmailValidation.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Gmail {
    String message() default "O email deve ser um endereço Gmail válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
