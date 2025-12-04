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
    public ResponseEntity<TravelRequest> criarViagem(@RequestBody TravelRequest viagem) {
        TravelRequest viagemCreated = travelService.criarViagem(viagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(viagemCreated);
    }

    @GetMapping
    public List<TravelRequest> listarViagens() {
        return travelService.listarViagens();
    }

    @GetMapping("/{id}")
    public TravelRequest getById(@PathVariable Long id) {
        return travelService.getById(id);
    }

    @PatchMapping("/{id}/aceitar/{driverId}")
    public TravelRequest aceitar(@PathVariable Long id, @PathVariable Long driverId) {
        return travelService.aceitarViagem(id, driverId);
    }

    @PatchMapping("/{id}/recusar/{driverId}")
    public TravelRequest refuse(@PathVariable Long id, @PathVariable Long driverId) {
        return travelService.recusarViagem(id, driverId);
    }

    @PatchMapping("/{id}/finalizar/{driverId}")
    public TravelRequest finish(@PathVariable Long id, @PathVariable Long driverId) {
        return travelService.finalizar(id, driverId);
    }
}
