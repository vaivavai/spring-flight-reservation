package com.example.FlightReservations.exceptions.invalidDTO.appUser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppUserNameMissingException extends RuntimeException{
  public AppUserNameMissingException() {
    super("App user name is missing");
  }
}
