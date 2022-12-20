package com.example.FlightReservations.dto;

import com.example.FlightReservations.exceptions.invalidDTO.flight.FlightIdMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.reservation.UserIdMissingException;
import com.sun.istack.NotNull;

public class ReservationDTO {

  @NotNull
  private String flightId;
  @NotNull
  private String appUserId;

  public void validateRequirements() {
    if(this.flightId == null || this.flightId.equals("")) {
      throw new FlightIdMissingException();
    }

    if(this.appUserId == null || this.appUserId.equals("")) {
      throw new UserIdMissingException();
    }
  }

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getAppUserId() {
    return appUserId;
  }

  public void setAppUserId(String appUserId) {
    this.appUserId = appUserId;
  }


  public ReservationDTO() {
  }
}
