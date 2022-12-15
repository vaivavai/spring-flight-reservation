package com.example.FlightReservations.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException{
  public ReservationNotFoundException() {
    super("Reservation not found");
  }
}
