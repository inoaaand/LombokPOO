package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @Size(min=2, max=50)
    String name;
    
    LocalDate birthDate;

    @NotBlank(message = "Digite seu cpf...")
    String cpf;

    @NotBlank(message = "Digite seu email...")
    String email;

}

//Em seu projeto cars, adicione os atributos cpf e email do tipo String.
// Insira as seguintes validações: 
// nome --> mínimo 3, máximo 50 caracteres, campo obrigatório e não pode aceitar espaços
// email --> campo obrigatório, não pode aceitar espaços em branco e deve estar no padrão de email
// cpf --> campo obrigatório, não pode aceitar espaços em branco e deve estar no padrão de cpf
//fazer rodar

//beans - verificações
//controller - validar