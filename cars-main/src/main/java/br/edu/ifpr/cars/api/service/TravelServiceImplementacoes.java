package br.edu.ifpr.cars.api.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TravelServiceImplementacoes implements TravelService {

    private final TravelRequestRepository travelRepository;
    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;

    @Override
    public TravelRequest criarViagem(TravelRequest travel) {

        if (travel.getOrigem() == null || travel.getOrigem().isBlank()
                || travel.getDestino() == null || travel.getDestino().isBlank()
                || travel.getPassenger() == null || travel.getPassenger().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "origem, destino e passenger.id são obrigatórios");
        }

        Passenger passenger = passengerRepository.findById(travel.getPassenger().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passageiro não encontrado"));

        travel.setPassenger(passenger);
        travel.setStatus(TravelStatus.CREATED);

        return travelRepository.save(travel);
    }

    @Override
    public List<TravelRequest> listarViagens() {
        return travelRepository.findAll();
    }

    @Override
    public TravelRequest getById(Long id) {
        return travelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));
    }

    @Override
    public TravelRequest aceitarViagem(Long id, Long driverId) {

        TravelRequest travel = getById(id);

        if (travel.getStatus() == TravelStatus.ACCEPTED || travel.getStatus() == TravelStatus.FINISHED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem já aceita ou finalizada.");
        }

        if (travel.getStatus() != TravelStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Apenas viagens CREATED podem ser aceitas.");
        }

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        travel.setDriver(driver);
        travel.setStatus(TravelStatus.ACCEPTED);

        return travelRepository.save(travel);
    }

    @Override
    public TravelRequest recusarViagem(Long id, Long driverId) {

        TravelRequest travel = getById(id);

        if (travel.getStatus() != TravelStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente viagens CREATED podem ser recusadas.");
        }

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        travel.setDriver(driver);
        travel.setStatus(TravelStatus.REFUSED);

        return travelRepository.save(travel);
    }

    @Override
    public TravelRequest finalizar(Long id, Long driverId) {

        TravelRequest travel = getById(id);

        if (travel.getStatus() != TravelStatus.ACCEPTED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Apenas viagens ACCEPTED podem ser finalizadas.");
        }

        if (travel.getDriver() == null || !travel.getDriver().getId().equals(driverId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente o motorista aceitou pode finalizar.");
        }

        travel.setStatus(TravelStatus.FINISHED);

        return travelRepository.save(travel);
    }
}
