package com.example.FlightReservations.models;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "flight")
public class Flight {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "pg-uuid")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "origin_airport")
  private Airport originAirport;

  @ManyToOne
  @JoinColumn(name = "destination_airport")
  private Airport destinationAirport;

  private LocalDateTime departureDate;

  private Double price;

  public Flight() {
  }

  public Flight(Airport originAirport, Airport destinationAirport,
      LocalDateTime departureDate, Double price) {
    this.originAirport = originAirport;
    this.destinationAirport = destinationAirport;
    this.departureDate = departureDate;
    this.price = price;
  }

  public Flight(UUID id, Airport originAirport, Airport destinationAirport,
      LocalDateTime departureDate, Double price) {
    this.id = id;
    this.originAirport = originAirport;
    this.destinationAirport = destinationAirport;
    this.departureDate = departureDate;
    this.price = price;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Airport getOriginAirport() {
    return originAirport;
  }

  public void setOriginAirport(Airport originAirport) {
    this.originAirport = originAirport;
  }

  public Airport getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(Airport destinationAirport) {
    this.destinationAirport = destinationAirport;
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