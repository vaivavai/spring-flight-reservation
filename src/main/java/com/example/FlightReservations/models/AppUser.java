package com.example.FlightReservations.models;

import com.example.FlightReservations.exceptions.invalidDTO.appUser.AppUserNameMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.appUser.AppUserRoleMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.appUser.EmailMissingException;
import com.example.FlightReservations.exceptions.invalidDTO.appUser.PasswordMissingException;
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

  public AppUser() {
  }

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

  public void validateRequirements() {
    if (this.name == null || this.name.equals("")) {
      throw new AppUserNameMissingException();
    }
    if (this.password == null || this.password.equals("")) {
      throw new PasswordMissingException();
    }
    if (this.email == null || this.email.equals("")) {
      throw new EmailMissingException();
    }
    if (this.appUserRole == null) {
      throw new AppUserRoleMissingException();
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


}

