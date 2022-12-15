package com.example.FlightReservations.services;

import static org.junit.jupiter.api.Assertions.*;

import com.example.FlightReservations.exceptions.notFound.ReservationNotFoundException;
import com.example.FlightReservations.repositories.ReservationRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class ReservationServiceTest {
  ReservationRepository reservationRepository;


  @Test
  void getReservations() {
  }

  @Test
  void getReservationsById_When_Id_IsNull_ThrowException() {
    assertThrows(RuntimeException.class, () -> Optional.of(reservationRepository.findById(null)).orElseThrow(
            ReservationNotFoundException::new));

//    @Test
//    public void whenIdIsNull_thenExceptionIsThrown() {
//      assertThrows(InvalidArgumentException.class, () -> Optional
//          .ofNullable(personRepository.findNameById(null))
//          .orElseThrow(InvalidArgumentException::new));
//    }
  }

  @Test
  void getReservationsByFlightId() {
  }

  @Test
  void getReservationsByUserId() {
  }
}