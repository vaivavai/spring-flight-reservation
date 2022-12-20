package com.example.FlightReservations.controllers;

import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.services.AirportService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/airports")
public class AirportController {

  private final AirportService airportService;

  @Autowired
  public AirportController(AirportService airportService) {
    this.airportService = airportService;
  }

  //Get all airports
  @GetMapping
  public List<Airport> getAirports() {
    return airportService.getAirports();
  }

  //Get by id
  @GetMapping(path = "{airportId}")
  public Airport getAirportById(@PathVariable UUID airportId) {
    return airportService.getAirportById(airportId);
  }

  //Create airport
  @PostMapping
  public void addNewAirport(@RequestBody Airport airport) {
    airport.validateRequirements();
    airportService.addNewAirport(airport);
  }

  //Delete airport
  @DeleteMapping(path = "{airportId}")
  public void deleteAirport(@PathVariable UUID airportId) {
    airportService.deleteAirport(airportId);
  }

  //Update airport
  @PutMapping(path = "{airportId}")
  public Airport updateAirport(@PathVariable UUID airportId, @RequestBody Airport airport) {
    airport.validateRequirements();
    return airportService.updateAirport(airportId, airport);
  }

}
