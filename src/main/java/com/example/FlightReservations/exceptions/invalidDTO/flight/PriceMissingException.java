package com.example.FlightReservations.exceptions.invalidDTO.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PriceMissingException extends RuntimeException{
  public PriceMissingException() {
    super("Origin airport id is missing");
  }
}
