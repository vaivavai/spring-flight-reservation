package com.example.FlightReservations.repositories;

import com.example.FlightReservations.models.Reservation;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>{

  List<Reservation> findReservationsByAppUserId(UUID appUserId);

  List<Reservation> findReservationsByFlightId(UUID flightId);

  List<Reservation> findReservationsByAppUserIdAndFlightId(UUID appUserId, UUID flightId);
}
