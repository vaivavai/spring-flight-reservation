package com.example.FlightReservations.exceptions.alreadyExists;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppUserAlreadyExistsException extends RuntimeException{

  public AppUserAlreadyExistsException() {
    super("User with this email already exists");
  }

}
