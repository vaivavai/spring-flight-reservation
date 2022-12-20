package com.example.FlightReservations.repositories;

import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.models.Flight;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository <Flight, UUID>{

  List<Flight> findByOriginAirport(Airport originAirport);

  List<Flight> findFlightsByOriginAirportAndDestinationAirport(Airport originAirport, Airport destinationAirport);

  List<Flight> findFlightsByDestinationAirport(Airport destinationAirport);

}
