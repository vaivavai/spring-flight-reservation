package com.example.FlightReservations.exceptions.invalidDTO.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DestinationAirportIdMissingException extends RuntimeException{
  public DestinationAirportIdMissingException() {
    super("Destination airport id is missing");
  }
}
