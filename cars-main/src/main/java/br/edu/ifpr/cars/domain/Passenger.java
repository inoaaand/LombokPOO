package br.edu.ifpr.cars.domain;

import br.edu.ifpr.cars.validate.Gmail;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Por favor, informe seu nome.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String name;

    @NotBlank(message = "Por favor, informe seu email.")
    @Gmail
    private String email;

}
