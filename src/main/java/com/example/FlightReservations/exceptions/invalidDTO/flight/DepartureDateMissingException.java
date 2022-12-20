package com.example.FlightReservations.exceptions.invalidDTO.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartureDateMissingException extends RuntimeException{
  public DepartureDateMissingException() {
    super("Departure date is missing");
  }
}
