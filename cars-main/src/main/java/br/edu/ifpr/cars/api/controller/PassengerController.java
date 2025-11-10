package br.edu.ifpr.cars.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifpr.cars.api.exception.ResourceNotFoundException;
import br.edu.ifpr.cars.domain.Passenger;
import br.edu.ifpr.cars.domain.PassengerRepository;

@RestController
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping("/passengers")
    public List<Passenger> listPassengers() {
        return passengerRepository.findAll();
    }

    @GetMapping("/passengers/{id}")
    public Passenger findPassenger(@PathVariable("id") Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger não encontrado com id: " + id));
    }

    @PostMapping("/passengers")
    public Passenger createPassenger(@RequestBody Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @PutMapping("/passengers/{id}")
    public Passenger fullUpdatePassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger) {
        Passenger foundPassenger = findPassenger(id);
        foundPassenger.setName(passenger.getName());
        foundPassenger.setEmail(passenger.getEmail());
        return passengerRepository.save(foundPassenger);
    }

    @PatchMapping("/passengers/{id}")
    public Passenger incrementalUpdatePassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger) {
        Passenger foundPassenger = findPassenger(id);

        foundPassenger.setName(Optional.ofNullable(passenger.getName()).orElse(foundPassenger.getName()));
        foundPassenger.setEmail(Optional.ofNullable(passenger.getEmail()).orElse(foundPassenger.getEmail()));

        return passengerRepository.save(foundPassenger);
    }

    @DeleteMapping("/passengers/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerRepository.deleteById(id);
    }
}
