package com.example.FlightReservations.controllers;

import com.example.FlightReservations.dto.FlightDTO;
import com.example.FlightReservations.models.Flight;
import com.example.FlightReservations.services.FlightService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "api/v1/flights")
public class FlightController {

  private final FlightService flightService;

  @Autowired
  public FlightController(FlightService flightService) {
    this.flightService = flightService;
  }

  // Get flight by id
  @GetMapping(path = "{flightId}")
  public Flight getFlightsById(@PathVariable UUID flightId) {
    Flight result = flightService.getFlightById(flightId);
    if (result == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return result;
  }

  // Get all flights by airport
  @GetMapping
  public List<Flight> getFlightsByAirport(
      @RequestParam(name = "originAirportId", required = false) UUID originAirportId,
      @RequestParam(name = "destinationAirportId", required = false) UUID destinationAirportId) {


    if (originAirportId != null && destinationAirportId != null) {
      return flightService.getFlightsByOriginAndDestinationAirportId(originAirportId,
          destinationAirportId);
    }

    if (originAirportId == null && destinationAirportId != null) {
      return flightService.findFlightsByDestinationAirport(destinationAirportId);
    }

    if (originAirportId != null && destinationAirportId == null) {
      return flightService.getFlightsByOriginAirportId(originAirportId);
    }

    return flightService.getFlights();
  }

  // Create flight
  @PostMapping
  public void addNewFlight(@Valid @RequestBody FlightDTO flightDTO) {
    flightDTO.validateRequirements();
    flightService.addNewFlight(flightDTO);
  }

  // Delete flight
  @DeleteMapping(path = "{flightId}")
  public void deleteFlight(@PathVariable("flightId") UUID flightId) {
    flightService.deleteFlight(flightId);
  }

  // Update flight
  @PutMapping(path = "{flightId}")
  public Flight updateFlight(@PathVariable("flightId") UUID flightId,
      @RequestBody FlightDTO flightDTO) {
    flightDTO.validateRequirements();
    return flightService.updateFlight(flightId, flightDTO);
  }
}
