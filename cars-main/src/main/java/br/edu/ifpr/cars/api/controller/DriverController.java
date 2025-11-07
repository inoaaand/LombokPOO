package br.edu.ifpr.cars.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.api.exception.ResourceNotFoundException;
import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.domain.DriverRepository;
import jakarta.validation.Valid;


@Service
@RestController
public class DriverController {

    @Autowired // INJEÇÃO DE DEPENDÊNCIAS
    DriverRepository driverRepository; // objeto instancia do repositório!

    @GetMapping("/drivers")
    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    // definir uma Exception personalizada!
    @GetMapping("/drivers/{id}")
    public Driver findDrivers(@PathVariable("id") Long id) {
        return driverRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Driver não encontrado com id: " + id));

    }

    @PostMapping("/drivers")
    public Driver createDriver(@RequestBody @Valid Driver driver) {
        return driverRepository.save(driver);
    }

    // update
    @PutMapping("/drivers{id}")
    public Driver fullUpdateDriver(@PathVariable("id") Long id, @RequestBody @Valid Driver driver) {
        Driver foundDriver = findDrivers(id);
        foundDriver.setName(driver.getName());
        foundDriver.setBirthDate(driver.getBirthDate());
        foundDriver.setCpf(driver.getCpf());
        foundDriver.setEmail(driver.getEmail());
        return driverRepository.save(foundDriver);

    }

    @PatchMapping("/drivers/{id}")
    public Driver incrementalUpdaDriver(@PathVariable("id") Long id, @RequestBody Driver driver) {
        Driver foundDriver = findDrivers(id);

        foundDriver.setName(Optional.ofNullable(driver.getName()).orElse(foundDriver.getName()));
        foundDriver.setBirthDate(Optional.ofNullable(driver.getBirthDate()).orElse(foundDriver.getBirthDate()));
        foundDriver.setCpf(Optional.ofNullable(driver.getCpf()).orElse(foundDriver.getCpf()));
        foundDriver.setEmail(Optional.ofNullable(driver.getEmail()).orElse(foundDriver.getEmail()))
        return driverRepository.save(foundDriver);

    }

    @DeleteMapping("/drivers/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverRepository.deleteById(id);
    }

}
