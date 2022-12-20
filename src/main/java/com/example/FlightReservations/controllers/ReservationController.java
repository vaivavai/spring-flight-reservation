package com.example.FlightReservations.controllers;

import com.example.FlightReservations.dto.ReservationDTO;
import com.example.FlightReservations.models.Reservation;
import com.example.FlightReservations.services.ReservationService;
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
@RequestMapping(path = "api/v1/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  @Autowired
  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  // Get all or by flightId or UserId
  @GetMapping
  public List<Reservation> getReservations(
      @RequestParam(name = "flightId", required = false) UUID flightId,
      @RequestParam(name = "appUserId", required = false) UUID appUserId) {

    if (flightId == null && appUserId != null) {
      return reservationService.getReservationsByUserId(appUserId);
    }
    if (flightId != null && appUserId == null) {
      return reservationService.getReservationsByFlightId(flightId);
    }
    if (flightId != null && appUserId != null) {
      return reservationService.getReservationsByUserIdAndFlightId(appUserId, flightId);
    }

    return reservationService.getReservations();
  }

  // Get by reservationId
  @GetMapping(path = "{reservationId}")
  public Reservation getReservationsById(@PathVariable UUID reservationId) {
    Reservation reservationsById = reservationService.getReservationsById(reservationId);
    return reservationsById;
  }

  // Create reservation
  @PostMapping
  public void addNewReservation(@RequestBody ReservationDTO reservationDTO) {
    reservationDTO.validateRequirements();
    reservationService.addReservation(reservationDTO);
  }

  // Delete reservation
  @DeleteMapping(path = "{reservationId}")
  public void deleteReservation(@PathVariable UUID reservationId) {
    reservationService.deleteReservation(reservationId);
  }

  // Update reservation
  @PutMapping(path = "{reservationId}")
  public void updateReservation(@PathVariable UUID reservationId,
      @RequestBody ReservationDTO reservationDTO) {
    reservationDTO.validateRequirements();
    reservationService.updateReservation(reservationId, reservationDTO);
  }

}
