package com.example.FlightReservations.models;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "reservation", uniqueConstraints = {
    @UniqueConstraint(name = "UniqueUserAndFlight", columnNames = {"app_user_id", "flight_id"}) //todo check why not validated
})
public class Reservation {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "pg-uuid")
  private UUID id;
  @ManyToOne
  @JoinColumn(nullable = false, name = "app_user_id", referencedColumnName = "id")
  private AppUser appUser;
  @ManyToOne
  @JoinColumn(nullable = false, name = "flight_id", referencedColumnName = "id")
  private Flight flight;

  public Reservation() {
  }

  public Reservation(AppUser appUser, Flight flight) {
    this.appUser = appUser;
    this.flight = flight;
  }

  public Reservation(UUID id, AppUser appUser, Flight flight) {
    this.id = id;
    this.appUser = appUser;
    this.flight = flight;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public AppUser getUser() {
    return appUser;
  }

  public void setUser(AppUser appUser) {
    this.appUser = appUser;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

}
