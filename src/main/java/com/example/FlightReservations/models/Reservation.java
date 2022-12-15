package com.example.FlightReservations.models;

import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

@Entity
@Table(name="reservation")
public class Reservation {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "pg-uuid")
  private UUID id;
  @ManyToOne
  @JoinColumn(nullable = false, name = "appUser_id", referencedColumnName = "id")
  private AppUser appUser;
  @ManyToOne
  @JoinColumn(nullable = false, name = "flight_id", referencedColumnName = "id")
  private Flight flight;

  public Reservation(AppUser appUser, Flight flight) {
    this.appUser = appUser;
    this.flight = flight;
  }

  public Reservation(UUID id, AppUser appUser, Flight flight) {
    this.id = id;
    this.appUser = appUser;
    this.flight = flight;
  }

  public Reservation() {

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

  @Override
  public String toString() {
    return "Reservation{" +
        "id=" + id +
        ", appUser=" + appUser +
        ", flight=" + flight +
        '}';
  }
}
