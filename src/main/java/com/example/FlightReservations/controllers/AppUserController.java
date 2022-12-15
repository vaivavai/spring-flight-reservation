package com.example.FlightReservations.controllers;

import com.example.FlightReservations.models.AppUser;
import com.example.FlightReservations.services.AppUserService;
import com.example.FlightReservations.utils.AppUserRole;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/users")
public class AppUserController {

  AppUserService appUserService;

  @Autowired
  public AppUserController(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @GetMapping
  public List<AppUser> getUsers() {
    return appUserService.getUsers();
  }

  @GetMapping(path = "{appUserId}")
  public AppUser getAppUsersById(@PathVariable UUID appUserId) {
    return appUserService.getAppUsersById(appUserId);
  }

  @PostMapping
  public void addNewAppUser(@RequestBody AppUser appUser) {
    appUserService.addNewAppUser(appUser);
  }

  @DeleteMapping(path = "{appUserId}")
  public void deleteAppUser(@PathVariable UUID appUserId) {
    appUserService.deleteAppUser(appUserId);
  }

  @PutMapping(path = "{appUserId}")
  public AppUser updateAppUser(@PathVariable UUID appUserId,
      @RequestBody AppUser appUser) {
    return appUserService.updateAppUser(appUserId, appUser);
  }

}
