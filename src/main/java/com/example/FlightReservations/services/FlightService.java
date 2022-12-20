package com.example.FlightReservations.services;

import com.example.FlightReservations.dto.FlightDTO;
import com.example.FlightReservations.exceptions.notFound.AirportNotFoundException;
import com.example.FlightReservations.exceptions.notFound.FlightNotFoundException;
import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.models.Flight;
import com.example.FlightReservations.repositories.AirportRepository;
import com.example.FlightReservations.repositories.FlightRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

  private final FlightRepository flightRepository;
  private final AirportRepository airportRepository;

  @Autowired
  public FlightService(FlightRepository flightRepository, AirportRepository airportRepository) {
    this.flightRepository = flightRepository;
    this.airportRepository = airportRepository;
  }

  public List<Flight> getFlights() {
    return flightRepository.findAll();
  }

  public Flight getFlightById(UUID flightId) {
    Optional<Flight> optionalFlight = flightRepository.findById(flightId);

    if (optionalFlight.isEmpty()) {
      throw new FlightNotFoundException();
    }
    return optionalFlight.get();

  }

  public void addNewFlight(FlightDTO flightDto) {

    Airport originAirport = getOriginAirport(flightDto);
    Airport destinationAirport = getDestinationAirport(flightDto);
    LocalDateTime departureDate = flightDto.getDepartureDate();
    Double price = flightDto.getPrice();

    Flight flight = new Flight();
    flight.setOriginAirport(originAirport);
    flight.setDestinationAirport(destinationAirport);
    flight.setDepartureDate(departureDate);
    flight.setPrice(price);

    flightRepository.save(flight);
  }

  public void deleteFlight(UUID flightId) {
    if (!flightRepository.existsById(flightId)) {
      throw new FlightNotFoundException();
    }
    flightRepository.deleteById(flightId);
  }

  public Flight updateFlight(UUID flightId,
      FlightDTO flightDTO) {
    Optional<Flight> optionalFlight = flightRepository.findById(flightId);
    if (optionalFlight.isPresent()) {
      Flight updatedFlight = optionalFlight.get();

      Airport originAirport = getOriginAirport(flightDTO);
      Airport destinationAirport = getDestinationAirport(flightDTO);

      updatedFlight.setOriginAirport(originAirport);
      updatedFlight.setDestinationAirport(destinationAirport);
      updatedFlight.setDepartureDate(flightDTO.getDepartureDate());
      updatedFlight.setPrice(flightDTO.getPrice());
      return flightRepository.save(updatedFlight);
    } else {
      throw new FlightNotFoundException();

    }
  }

  public List<Flight> getFlightsByOriginAirportId(UUID originAirportId) {
    Optional<Airport> optionalAirport = airportRepository.findById(originAirportId);
    if (optionalAirport.isPresent()) {
      List<Flight> flights = flightRepository.findByOriginAirport(optionalAirport.get());
      if (!flights.isEmpty()) {
        return flights;
      } else {
        throw new FlightNotFoundException();
      }
    } else {
      throw new AirportNotFoundException();
    }
  }

  public List<Flight> getFlightsByOriginAndDestinationAirportId(UUID originAirportId,
      UUID destinationAirportId) {

    Optional<Airport> optionalOriginAirport = airportRepository.findById(originAirportId);
    Optional<Airport> optionalDestinationAirport = airportRepository.findById(destinationAirportId);
    if (optionalOriginAirport.isPresent() && optionalDestinationAirport.isPresent()) {
      List<Flight> flights = flightRepository.findFlightsByOriginAirportAndDestinationAirport(
          optionalOriginAirport.get(), optionalDestinationAirport.get());
      if (!flights.isEmpty()) {
        return flights;
      } else {
        throw new FlightNotFoundException();
      }
    } else {
      throw new AirportNotFoundException();
    }
  }

  public List<Flight> findFlightsByDestinationAirport(UUID destinationAirportId) {
    Optional<Airport> optionalAirport = airportRepository.findById(destinationAirportId);
    if (optionalAirport.isEmpty()) {
      throw new AirportNotFoundException();
    } else {
      List<Flight> flights = flightRepository.findFlightsByDestinationAirport(
          optionalAirport.get());
      if (!flights.isEmpty()) {
        return flights;
      } else {
        throw new FlightNotFoundException();
      }
    }
  }

  private Airport getDestinationAirport(FlightDTO flightDto) {
    Optional<Airport> optionalDestinationAirport = airportRepository.findById(
        UUID.fromString(flightDto.getDestinationAirportId()));
    if (optionalDestinationAirport.isEmpty()) {
      throw new AirportNotFoundException();
    }

    Airport destinationAirport = optionalDestinationAirport.get();
    return destinationAirport;
  }

  private Airport getOriginAirport(FlightDTO flightDto) {
    Optional<Airport> optionalOriginAirport = airportRepository.findById(
        UUID.fromString(flightDto.getOriginAirportId()));
    if (optionalOriginAirport.isEmpty()) {
      throw new AirportNotFoundException();
    }
    Airport originAirport = optionalOriginAirport.get();
    return originAirport;
  }
}



