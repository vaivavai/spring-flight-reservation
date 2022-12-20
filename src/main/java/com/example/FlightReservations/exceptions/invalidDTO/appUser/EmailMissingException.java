package com.example.FlightReservations.exceptions.invalidDTO.appUser;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailMissingException extends RuntimeException{
  public EmailMissingException() {
    super("App user email is missing");
  }
}
