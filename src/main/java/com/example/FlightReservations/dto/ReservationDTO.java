package com.example.FlightReservations.dto;

import com.sun.istack.NotNull;

public class ReservationDTO {

  @NotNull
  private String flightId;
  @NotNull
  private String userId;

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public ReservationDTO(String flightId ,String userId) {
    this.flightId = flightId;
    this.userId = userId;
  }
}
