package br.edu.ifpr.cars.api.service;

import java.util.List;

import br.edu.ifpr.cars.domain.TravelRequest;

public interface TravelService {

    TravelRequest createTravel(TravelRequest travel);

    List<TravelRequest> listAll();

    TravelRequest getById(Long id);

    TravelRequest acceptTravel(Long id, Long driverId);

    TravelRequest refuseTravel(Long id, Long driverId);

    TravelRequest finishTravel(Long id, Long driverId);
}
