package com.example.FlightReservations.models;

import com.example.FlightReservations.exceptions.invalidDTO.airport.AirportNameMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.airport.CityMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.airport.CountryMissingException;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "airport")
@Table
public class Airport {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "pg-uuid")
  private UUID id;
  private String name;
  private String country;
  private String city;

  public Airport() {
  }

  public Airport(String name, String city, String country) {
    this.name = name;
    this.city = city;
    this.country = country;
  }

  public Airport(UUID id, String name, String country, String city) {
    this.id = id;
    this.name = name;
    this.country = country;
    this.city = city;
  }

  public void validateRequirements() {
    if (this.name == null || this.name.equals("")) {
      throw new AirportNameMissingException();
    }

    if (this.country == null || this.country.equals("")) {
      throw new CountryMissingException();
    }
    if (this.city == null || this.city.equals("")) {
      throw new CityMissingException();
    }

  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

}