package com.example.FlightReservations.exceptions.invalidDTO.appUser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppUserRoleMissingException extends RuntimeException{
  public AppUserRoleMissingException() {
    super("App user role is missing");
  }
}
