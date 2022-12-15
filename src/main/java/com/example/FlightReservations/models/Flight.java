package com.example.FlightReservations.models;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
  private String originCity;
  private String destinationCity;
  private LocalDateTime departureDate;
  private Double price;

  public Flight() {
  }

  public Flight(String originCity, String destinationCity, LocalDateTime departureDate,
      Double price) { // TODO Check if Double class is used consistently
    this.originCity = originCity;
    this.destinationCity = destinationCity;
    this.departureDate = departureDate;
    this.price = price;
  }

  public Flight(UUID id, String originCity, String destinationCity, LocalDateTime departureDate,
      Double price) {
    this.id = id;
    this.originCity = originCity;
    this.destinationCity = destinationCity;
    this.departureDate = departureDate;
    this.price = price;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getOriginCity() {
    return originCity;
  }

  public void setOriginCity(String originCity) {
    this.originCity = originCity;
  }

  public String getDestinationCity() {
    return destinationCity;
  }

  public void setDestinationCity(String destinationCity) {
    this.destinationCity = destinationCity;
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

  @Override
  public String toString() {
    return "Flight{" +
        "id=" + id +
        ", originCity=" + originCity +
        ", destinationCity=" + destinationCity +
        ", departureDate=" + departureDate +
        ", price=" + price +
        '}';
  }
}