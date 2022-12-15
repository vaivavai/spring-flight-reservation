package com.example.FlightReservations.repositories;

import com.example.FlightReservations.models.Flight;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository <Flight, UUID>{

  @Override
  Optional<Flight> findById(UUID uuid);

  Optional<Flight> findByOriginCity(String originCity);

  List<Flight> findFlightsByOriginCityAndDestinationCity(String originCity, String destinationCity);

  List<Flight> findFlightsByDestinationCity(String destinationCityName);

  List<Flight> findFlightsByOriginCity(String originCityName);
}
