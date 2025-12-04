package br.edu.ifpr.cars.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.cars.api.service.TravelService;
import br.edu.ifpr.cars.domain.TravelRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @PostMapping
    public ResponseEntity<TravelRequest> create(@RequestBody TravelRequest travel) {
        TravelRequest created = travelService.createTravel(travel);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<TravelRequest> list() {
        return travelService.listAll();
    }

    @GetMapping("/{id}")
    public TravelRequest getById(@PathVariable Long id) {
        return travelService.getById(id);
    }

    @PatchMapping("/{id}/accept/{driverId}")
    public TravelRequest accept(@PathVariable Long id, @PathVariable Long driverId) {
        return travelService.acceptTravel(id, driverId);
    }

    @PatchMapping("/{id}/refuse/{driverId}")
    public TravelRequest refuse(@PathVariable Long id, @PathVariable Long driverId) {
        return travelService.refuseTravel(id, driverId);
    }

    @PatchMapping("/{id}/finish/{driverId}")
    public TravelRequest finish(@PathVariable Long id, @PathVariable Long driverId) {
        return travelService.finishTravel(id, driverId);
    }
}
