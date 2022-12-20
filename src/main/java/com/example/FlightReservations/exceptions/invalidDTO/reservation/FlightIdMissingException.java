package com.example.FlightReservations.exceptions.invalidDTO.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FlightIdMissingException extends RuntimeException{
  public FlightIdMissingException() {
    super("Flight id is missing");
  }
}
