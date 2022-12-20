package com.example.FlightReservations.exceptions.invalidDTO.airport;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CityMissingException extends RuntimeException{
  public CityMissingException() {
    super("Airport city is missing");
  }
}
