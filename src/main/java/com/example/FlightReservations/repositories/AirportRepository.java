package com.example.FlightReservations.repositories;

import com.example.FlightReservations.models.Airport;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, UUID> {

  Optional<Airport> findAirportByName(String name);
}
