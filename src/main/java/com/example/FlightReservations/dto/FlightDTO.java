package com.example.FlightReservations.dto;

import com.example.FlightReservations.exceptions.invalidDTO.flight.DepartureDateMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.flight.DestinationAirportIdMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.flight.OriginAirportIdMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.flight.PriceMissingException;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;

public class FlightDTO {

  @NotNull
  private String destinationAirportId;
  @NotNull
  private String originAirportId;
  @NotNull
  private LocalDateTime departureDate;
  @NotNull
  private Double price;

  public FlightDTO() {}

  public FlightDTO(String destinationAirportId, String originAirportId, LocalDateTime departureDate,
      Double price) {
    this.destinationAirportId = destinationAirportId;
    this.originAirportId = originAirportId;
    this.departureDate = departureDate;
    this.price = price;
  }

  public void validateRequirements() {
    if(this.destinationAirportId == null || this.destinationAirportId.equals("")) {
      throw new OriginAirportIdMissingException();
    }

    if(this.originAirportId == null || this.originAirportId.equals("")) {
      throw new DestinationAirportIdMissingException();
    }

    if(this.departureDate == null) {
      throw new DepartureDateMissingException();
    }

    if(this.price == null || this.price <= 0) {
      throw new PriceMissingException();
    }
  }

  public String getDestinationAirportId() {
    return destinationAirportId;
  }

  public void setDestinationAirportId(String destinationAirportId) {
    this.destinationAirportId = destinationAirportId;
  }

  public String getOriginAirportId() {
    return originAirportId;
  }

  public void setOriginAirportId(String originAirportId) {
    this.originAirportId = originAirportId;
  }

  public LocalDateTime getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(LocalDateTime departureDate) {
    this.departureDate = departureDate;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

}
