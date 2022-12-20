package com.example.FlightReservations.services;

import com.example.FlightReservations.exceptions.alreadyExists.AirportAlreadyExistsException;
import com.example.FlightReservations.exceptions.notFound.AirportNotFoundException;
import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.repositories.AirportRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

  private final AirportRepository airportRepository;

  @Autowired
  public AirportService(AirportRepository airportRepository1) {
    this.airportRepository = airportRepository1;
  }

  public List<Airport> getAirports() {
    return airportRepository.findAll();
  }

  public Airport getAirportById(UUID airportId) {
    Optional<Airport> optionalAirport = airportRepository.findById(airportId);
    if (optionalAirport.isEmpty()) {
      throw new AirportNotFoundException();
    }
    return optionalAirport.get();
  }

  public void addNewAirport(Airport airport) {
    Optional<Airport> optionalAirport = airportRepository.findAirportByName(airport.getName());
    if (optionalAirport.isPresent()) {
      throw new AirportAlreadyExistsException();
    }
    airportRepository.save(airport);
  }

  public void deleteAirport(UUID airportId) {
    if (!airportRepository.existsById(airportId)) {
      throw new AirportNotFoundException();
    }
    airportRepository.deleteById(airportId);
  }

  public Airport updateAirport(UUID airportId, Airport airport) {
    Optional<Airport> optionalAirport = airportRepository.findById(airportId);
    if (optionalAirport.isPresent()) {
      Airport updatedAirport = optionalAirport.get();
      updatedAirport.setName(airport.getName());
      updatedAirport.setCity(airport.getCity());
      updatedAirport.setCountry(airport.getCountry());
      return airportRepository.save(updatedAirport);
    } else {
      throw new AirportNotFoundException();
    }

  }
}

