package com.example.FlightReservations.models;

import com.example.FlightReservations.utils.AppUserRole;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "appUser")
public class AppUser {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Type(type = "pg-uuid")
  private UUID id;
  private String name;
  private String password;
  private String email;
  private AppUserRole appUserRole;

  public AppUser(String name, String password, String email, AppUserRole appUserRole) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.appUserRole = appUserRole;
  }

  public AppUser(UUID id, String name, String password, String email, AppUserRole appUserRole) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.email = email;
    this.appUserRole = appUserRole;
  }

  public AppUser() {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AppUserRole getAppUserRole() {
    return appUserRole;
  }

  public void setAppUserRole(AppUserRole appUserRole) {
    this.appUserRole = appUserRole;
  }

  @Override
  public String toString() {
    return "AppUser{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", email='" + email + '\'' +
        ", appUserRole=" + appUserRole +
        '}';
  }
}

