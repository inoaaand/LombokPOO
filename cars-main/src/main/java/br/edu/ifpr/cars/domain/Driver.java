package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.edu.ifpr.cars.validate.AnoFabricacao;
import br.edu.ifpr.cars.validate.CNH;
import br.edu.ifpr.cars.validate.Comentario;
import br.edu.ifpr.cars.validate.Placa;
import jakarta.persistence.Access;
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

    @NotBlank(message = "Digite seu nome...")
    @Size(min = 2, max = 50, message = "Seu nome deve ter no minímo 2 caracteres e no máximo 50.")
    String name;

    LocalDate birthDate;

    @NotBlank(message = "Digite seu cpf...")
    @CPF(message = "O CPF não é valido!")
    String cpf;

    @NotBlank(message = "Digite seu email...")
    @Email(message = "O email não é valido!")
    String email;


    @Placa(message = "Digite a placa")
    String placa;

    @CNH(message = "Digite a CNH")
    String cnh;

    @AnoFabricacao(message = "Digite o ano de fabricação")
    int anoCarro;

    @Comentario(message = "Digite seu comentário!")
    String comentario;

}
