package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.edu.ifpr.cars.validate.AnoFabricacao;
import br.edu.ifpr.cars.validate.CNH;
import br.edu.ifpr.cars.validate.Comentario;
import br.edu.ifpr.cars.validate.Placa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue
    Long id;

    @NotBlank(message = "Por favor, informe seu nome.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    String name;

    LocalDate birthDate;

    @NotBlank(message = "Por favor, informe seu CPF.")
    @CPF(message = "O CPF informado não é válido.")
    String cpf;

    @NotBlank(message = "Por favor, informe seu email.")
    @Email(message = "O email informado não é válido.")
    String email;

    @NotBlank(message = "Por favor, informe a placa do veículo.")
    @Placa
    String placa;
    
    @NotBlank(message = "Por favor, informe a CNH.")
    @CNH
    String cnh;    

    @AnoFabricacao(message = "Informe o ano de fabricação do veículo.")
    Integer anoCarro;

    @Comentario(message = "Deixe um comentário sobre você ou seu veículo.")
    String comentario;
}
