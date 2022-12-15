package com.example.FlightReservations.services;

import com.example.FlightReservations.exceptions.notFound.FlightNotFoundException;
import com.example.FlightReservations.models.Flight;
import com.example.FlightReservations.repositories.FlightRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

  private final FlightRepository flightRepository;

  @Autowired
  public FlightService(FlightRepository flightRepository) {
    this.flightRepository = flightRepository;
  }

  public List<Flight> getFlights() {
    return flightRepository.findAll();
  }

  public Flight getFlightsById(UUID flightId) { //TODO naudoti optional ar ne?
//    Optional<Flight> optionalFlight = flightRepository.findById(flightId);

    Flight flight = flightRepository.findById(flightId).orElseThrow(FlightNotFoundException::new);
//
//    if (optionalFlight.isEmpty()) {
//      throw new FlightNotFoundException();
//    }
    return flight;

  }

  public void addNewFlight(Flight flight) {
    flightRepository.save(flight);
  }

  public void deleteFlight(UUID flightId) {
//    boolean exists = flightRepository.existsById(flightId);
//    if (!exists) {
//      throw new IllegalStateException("Flight with id " + flightId + " does not exist.");
//    }

    Flight flight = flightRepository.findById(flightId).orElseThrow(FlightNotFoundException::new);

    flightRepository.deleteById(flightId);
  }

  public Flight updateFlight(UUID flightId, Flight flight) { //TODO ar geriau daryti kaip "updateAppUser" metode su transactional
    Optional<Flight> optionalFlight = flightRepository.findById(flightId);
    if (optionalFlight.isPresent()) {
      Flight updatedFlight = optionalFlight.get();
      updatedFlight.setOriginCity(flight.getOriginCity());
      updatedFlight.setDestinationCity(flight.getDestinationCity());
      updatedFlight.setDepartureDate(flight.getDepartureDate());
      updatedFlight.setPrice(flight.getPrice());
      return flightRepository.save(updatedFlight);
    } else {
      throw new FlightNotFoundException();

    }
  }

//  public Flight getFlightByOriginCity(String originCity) {
//    Optional<Flight> optionalFlight =   flightRepository.findByOriginCity(originCity);
//
//    if (optionalFlight.isEmpty())  return null;
//
//    return optionalFlight.get();
//
//  }

  public List<Flight> getFlightsByOriginAndDestination(String originCityName,
      String destinationCityName) {
    List<Flight> flightsByOriginCityAndDestinationCity = flightRepository.findFlightsByOriginCityAndDestinationCity(
        originCityName, destinationCityName);
    if (flightsByOriginCityAndDestinationCity.isEmpty()) {
      throw new FlightNotFoundException(); //TODO message pridet visur
    }
    return flightsByOriginCityAndDestinationCity;
  }

  public List<Flight> getFlightsByDestinationCityName(String destinationCityName) {
    List<Flight> flightsByDestinationCity = flightRepository.findFlightsByDestinationCity(
        destinationCityName);
    if (flightsByDestinationCity.isEmpty()) {
      throw new FlightNotFoundException();
    }

    return flightsByDestinationCity;
  }

  public List<Flight> getFlightsByOriginCityName(String originCityName) {
    List<Flight> flightsByOriginCity = flightRepository.findFlightsByOriginCity(
        originCityName);
    if (flightsByOriginCity.isEmpty()) {
      throw new FlightNotFoundException();
    }

    return flightsByOriginCity;
  }
}



