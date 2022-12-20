package com.example.FlightReservations.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {
  public FlightNotFoundException() {
    super("Flight not found");
  }

  public FlightNotFoundException(String message) {
    super(message);
  }
}
