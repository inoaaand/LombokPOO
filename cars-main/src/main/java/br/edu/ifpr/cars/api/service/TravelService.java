package br.edu.ifpr.cars.api.service;

import java.util.List;

import br.edu.ifpr.cars.domain.TravelRequest;

public interface TravelService {

    TravelRequest criarViagem(TravelRequest viagem);

    List<TravelRequest> listarViagens();

    TravelRequest getById(Long id);

    TravelRequest aceitarViagem(Long id, Long driverId);

    TravelRequest recusarViagem(Long id, Long driverId);

    TravelRequest finalizar(Long id, Long driverId);
}
