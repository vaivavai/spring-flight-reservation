package com.example.FlightReservations.services;

import com.example.FlightReservations.dto.ReservationDTO;
import com.example.FlightReservations.exceptions.notFound.AppUserNotFoundException;
import com.example.FlightReservations.exceptions.notFound.FlightNotFoundException;
import com.example.FlightReservations.exceptions.notFound.ReservationNotFoundException;
import com.example.FlightReservations.models.AppUser;
import com.example.FlightReservations.models.Flight;
import com.example.FlightReservations.models.Reservation;
import com.example.FlightReservations.repositories.AppUserRepository;
import com.example.FlightReservations.repositories.FlightRepository;
import com.example.FlightReservations.repositories.ReservationRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final AppUserRepository appUserRepository;
  private final FlightRepository flightRepository;

  @Autowired
  public ReservationService(ReservationRepository reservationRepository,
      AppUserRepository appUserRepository, FlightRepository flightRepository) {
    this.reservationRepository = reservationRepository;
    this.appUserRepository = appUserRepository;
    this.flightRepository = flightRepository;
  }

  public List<Reservation> getReservations() {
    List<Reservation> reservations = reservationRepository.findAll();
    return reservations;
  }

  public void addReservation(ReservationDTO reservationDTO) {

    AppUser appUser = getAppUser(reservationDTO);
    Flight flight = getFlight(reservationDTO);

    Reservation reservation = new Reservation();

    reservation.setUser(appUser);
    reservation.setFlight(flight);

    reservationRepository.save(reservation);
  }


  public void deleteReservation(UUID reservationId) {
    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow(ReservationNotFoundException::new);

    reservationRepository.deleteById(reservationId);
  }

  public Reservation getReservationsById(UUID reservationId) {
    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow(ReservationNotFoundException::new);
    return reservation;
  }

  public void updateReservation(UUID reservationId, ReservationDTO reservationDTO) {
    Reservation reservation = reservationRepository.findById(reservationId)
        .orElseThrow(ReservationNotFoundException::new);

    AppUser appUser = getAppUser(reservationDTO);
    Flight flight = getFlight(reservationDTO);

    reservation.setUser(appUser);
    reservation.setFlight(flight);

    reservationRepository.save(reservation);

  }

  public List<Reservation> getReservationsByFlightId(UUID flightId) {

    Flight flight = flightRepository.findById(flightId).orElseThrow(FlightNotFoundException::new);

    List<Reservation> reservations = reservationRepository.findReservationsByFlightId(flightId);
    if (reservations.isEmpty()) {
      throw new ReservationNotFoundException();
    }

    return reservations;
  }

  public List<Reservation> getReservationsByUserId(UUID appUserId) {

    AppUser user = appUserRepository.findById(appUserId).orElseThrow(AppUserNotFoundException::new);

    List<Reservation> reservations = reservationRepository.findReservationsByAppUserId(appUserId);

    if (reservations.isEmpty()) {
      throw new ReservationNotFoundException();
    }

    return reservations;

  }

  public List<Reservation> getReservationsByUserIdAndFlightId(UUID appUserId, UUID flightId) {
    AppUser appUser = appUserRepository.findById(appUserId)
        .orElseThrow(AppUserNotFoundException::new);
    Flight flight = flightRepository.findById(flightId).orElseThrow(FlightNotFoundException::new);
    List<Reservation> reservationsByUserIdAndFlightId = reservationRepository.findReservationsByAppUserIdAndFlightId(
        appUserId, flightId);
    if (reservationsByUserIdAndFlightId.isEmpty()) {
      throw new ReservationNotFoundException();
    }
    return reservationsByUserIdAndFlightId;
  }
  private Flight getFlight(ReservationDTO reservationDTO) {
    Optional<Flight> optionalFlight = flightRepository.findById(
        UUID.fromString(reservationDTO.getFlightId()));
    if (optionalFlight.isEmpty()) {
      throw new FlightNotFoundException();
    }

    Flight flight = optionalFlight.get();
    return flight;
  }

  private AppUser getAppUser(ReservationDTO reservationDTO) {
    Optional<AppUser> optionalAppUser = appUserRepository.findById(
        UUID.fromString(reservationDTO.getAppUserId()));
    if (optionalAppUser.isEmpty()) {
      throw new AppUserNotFoundException();
    }
    AppUser appUser = optionalAppUser.get();
    return appUser;
  }
}
