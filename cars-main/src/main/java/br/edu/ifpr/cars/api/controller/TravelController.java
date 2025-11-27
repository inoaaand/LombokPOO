package br.edu.ifpr.cars.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.domain.DriverRepository;
import br.edu.ifpr.cars.domain.Passenger;
import br.edu.ifpr.cars.domain.PassengerRepository;
import br.edu.ifpr.cars.domain.TravelRequest;
import br.edu.ifpr.cars.domain.TravelRequestRepository;
import br.edu.ifpr.cars.domain.TravelStatus;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private TravelRequestRepository travelRequestRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private DriverRepository driverRepository;

    @PostMapping()
    public ResponseEntity<TravelRequest> createTravel(@RequestBody TravelRequest travel) {

        if (travel.getOrigem() == null || travel.getOrigem().isBlank()
                || travel.getDestino() == null || travel.getDestino().isBlank()
                || travel.getPassenger() == null || travel.getPassenger().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "origem, destino e passenger.id são obrigatórios");
        }

        Passenger passenger = passengerRepository.findById(travel.getPassenger().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger não encontrado"));

        travel.setPassenger(passenger);
        travel.setStatus(TravelStatus.CREATED);

        TravelRequest saved = travelRequestRepository.save(travel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<TravelRequest> listTravels() {
        return travelRequestRepository.findAll();
    }

    @GetMapping("/{id}")
    public TravelRequest getTravel(@PathVariable Long id) {
        return travelRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel não encontrada"));
    }

    @PatchMapping("/{id}/accept/{driverId}")
    @Transactional
    public ResponseEntity<TravelRequest> acceptTravel(@PathVariable Long id, @PathVariable Long driverId) {
        TravelRequest travel = travelRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel não encontrada"));

        if (travel.getStatus() == TravelStatus.ACCEPTED || travel.getStatus() == TravelStatus.FINISHED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Viagem já aceita ou finalizada.");
        }
        if (travel.getStatus() != TravelStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente viagens com status CREATED podem ser aceitas.");
        }

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver não encontrado"));

        travel.setDriver(driver);
        travel.setStatus(TravelStatus.ACCEPTED);
        TravelRequest updated = travelRequestRepository.save(travel);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/refuse/{driverId}")
    @Transactional
    public ResponseEntity<TravelRequest> refuseTravel(@PathVariable Long id, @PathVariable Long driverId) {
        TravelRequest travel = travelRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel não encontrada"));

        if (travel.getStatus() != TravelStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente viagens com status CREATED podem ser recusadas.");
        }

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver não encontrado"));

        travel.setDriver(driver);
        travel.setStatus(TravelStatus.REFUSED);
        TravelRequest updated = travelRequestRepository.save(travel);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/finish/{driverId}")
    @Transactional
    public ResponseEntity<TravelRequest> finishTravel(@PathVariable Long id, @PathVariable Long driverId) {
        TravelRequest travel = travelRequestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Travel não encontrada"));

        if (travel.getStatus() != TravelStatus.ACCEPTED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível finalizar viagens em status ACCEPTED.");
        }

        if (travel.getDriver() == null || !travel.getDriver().getId().equals(driverId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente o motorista atribuído pode finalizar a viagem.");
        }

        travel.setStatus(TravelStatus.FINISHED);
        return ResponseEntity.ok(travelRequestRepository.save(travel));
    }
}
