package com.example.FlightReservations.exceptions.invalidDTO.appUser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordMissingException extends RuntimeException{
  public PasswordMissingException() {
    super("App user password is missing");
  }
}
