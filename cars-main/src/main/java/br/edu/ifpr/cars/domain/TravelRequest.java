package br.edu.ifpr.cars.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TravelRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe a cidade de origem.")
    private String origem;

    @NotBlank(message = "Informe a cidade de destino.")
    private String destino;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }
}
