package br.edu.ifpr.cars.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class TravelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe a cidade de origem.")
    private String origem;

    @NotBlank(message = "Informe a cidade de destino.")
    private String destino;

    @ManyToOne(optional = false)
    private Passenger passenger;

    @ManyToOne
    private Driver driver;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TravelStatus status;

}
