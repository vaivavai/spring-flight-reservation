package com.example.FlightReservations.exceptions.alreadyExists;

public class AppUserAlreadyExistsException extends RuntimeException{

  public AppUserAlreadyExistsException() {
    super("User with this email already exists");
  }

}
