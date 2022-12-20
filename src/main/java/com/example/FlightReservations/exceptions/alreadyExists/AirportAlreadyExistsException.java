package com.example.FlightReservations.exceptions.alreadyExists;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AirportAlreadyExistsException extends RuntimeException{

  public AirportAlreadyExistsException() {
    super("Airport with this name already exists");
  }

}
