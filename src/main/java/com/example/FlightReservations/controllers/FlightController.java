package com.example.FlightReservations.controllers;

import com.example.FlightReservations.exceptions.notFound.FlightNotFoundException;
import com.example.FlightReservations.models.Flight;
import com.example.FlightReservations.services.FlightService;
import java.util.List;
import java.util.UUID;
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


  @GetMapping(path = "{flightId}")
  public Flight getFlightsById(@PathVariable UUID flightId) {
    Flight result = flightService.getFlightsById(flightId);
    if (result == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return result;
  }

  @GetMapping
  public List<Flight> getFlightsByCity(
      @RequestParam(name = "originCityName", required = false) String originCityName, //TODO search by airport id, not city name
      @RequestParam(name = "destinationCityName", required = false) String destinationCityName) {

    if(originCityName == null && destinationCityName == null) {
      return flightService.getFlights();
    }

    if(originCityName != null && destinationCityName != null) {
      return flightService.getFlightsByOriginAndDestination(originCityName, destinationCityName);
    }

    if(originCityName == null && destinationCityName != null) {
      return flightService.getFlightsByDestinationCityName(destinationCityName);
    }

    if(originCityName != null && destinationCityName == null) {
      return flightService.getFlightsByOriginCityName(originCityName);
    }

    throw new FlightNotFoundException(); //TODO ar cia reik validacijos?
  }

  @PostMapping
  public void addNewFlight(@RequestBody Flight flight) {
    flightService.addNewFlight(flight);
  }

  @DeleteMapping(path = "{flightId}")
  public void deleteFlight(@PathVariable("flightId") UUID flightId) {
    flightService.deleteFlight(flightId);
  }

  @PutMapping(path = "{flightId}")
  public Flight updateFlight(@PathVariable("flightId") UUID flightId,
      @RequestBody Flight flight) {
    return flightService.updateFlight(flightId, flight);
  }


}
