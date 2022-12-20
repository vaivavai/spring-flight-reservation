package com.example.FlightReservations.exceptions.invalidDTO.reservation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserIdMissingException extends RuntimeException{
  public UserIdMissingException() {
    super("User id is missing");
  }
}
