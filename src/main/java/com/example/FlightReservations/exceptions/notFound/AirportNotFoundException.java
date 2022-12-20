package com.example.FlightReservations.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException{
  public AirportNotFoundException() {
    super("Airport not found");
  }
  public AirportNotFoundException(String msg) {
    super(msg);
  }
}
