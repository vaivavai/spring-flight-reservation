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

  @GetMapping
  public List<Reservation> getReservations() {
    return reservationService.getReservations();
  }

  @GetMapping(path = "{reservationId}")
  public Reservation getReservationsById(@PathVariable UUID reservationId) {
    Reservation reservationsById = reservationService.getReservationsById(reservationId);
    return reservationsById;
  }
@GetMapping(path = "flight/{flightId}") //TODO pakeist pagal parametra
  public List<Reservation> getReservationsByFlightId(@PathVariable UUID flightId) {
    return reservationService.getReservationsByFlightId(flightId);
  }

  @GetMapping(path = "/user") //TODO decide ar path ar requestparam
  public List<Reservation> getReservationsByUserId(@RequestParam(name = "userId") UUID userId) {
    return reservationService.getReservationsByUserId(userId);
  }

  @PostMapping
  public void addNewReservation(@RequestBody ReservationDTO reservationDTO) {
    reservationService.addReservation(reservationDTO);
  }

  @DeleteMapping(path= "{reservationId}")
  public  void deleteReservation(@PathVariable UUID reservationId) {
    reservationService.deleteReservation(reservationId);
  }
  @PutMapping(path= "{reservationId}")
  public  void updateReservation(@PathVariable UUID reservationId, @RequestBody ReservationDTO reservationDTO) {
    reservationService.updateReservation(reservationId, reservationDTO);
  }



}
