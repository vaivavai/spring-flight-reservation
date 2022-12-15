package com.example.FlightReservations.services;

import com.example.FlightReservations.exceptions.alreadyExists.AppUserAlreadyExistsException;
import com.example.FlightReservations.exceptions.notFound.AppUserNotFoundException;
import com.example.FlightReservations.models.AppUser;
import com.example.FlightReservations.repositories.AppUserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

  private final AppUserRepository appUserRepository;

  @Autowired
  public AppUserService(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  public List<AppUser> getUsers() {
    return appUserRepository.findAll();
  }

  public AppUser getAppUsersById(UUID appUserId) {
    Optional<AppUser> appUser = appUserRepository.findById(appUserId);
    if (appUser.isEmpty()) {
      throw new AppUserNotFoundException();
    }

    return appUser.get();
  }

  public void addNewAppUser(AppUser appUser) {
//    appUserRepository.findAppUserByEmail(appUser.getEmail());
    Optional<AppUser> optionalAppUser = appUserRepository.findAppUserByEmail(appUser.getEmail());
    if (optionalAppUser.isPresent()) {
      throw new AppUserAlreadyExistsException();
    }

    appUserRepository.save(appUser);

  }

  public void deleteAppUser(UUID appUserId) {
    if (!appUserRepository.existsById(appUserId)) {
      throw new AppUserNotFoundException();
    }
    appUserRepository.deleteById(appUserId);
  }

  public AppUser updateAppUser(UUID appUserId, AppUser appUser) {
    Optional<AppUser> optionalAppUser = appUserRepository.findById(appUserId);
    if (optionalAppUser.isPresent()) {
      AppUser updatedAppUser = optionalAppUser.get();

      updatedAppUser.setName(appUser.getName());
      updatedAppUser.setPassword(appUser.getPassword());
      updatedAppUser.setEmail(appUser.getEmail());
      updatedAppUser.setAppUserRole(appUser.getAppUserRole());
      return appUserRepository.save(updatedAppUser);
    } else {
      throw new AppUserNotFoundException();
    }
  }
}

