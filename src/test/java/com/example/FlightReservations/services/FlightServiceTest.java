package com.example.FlightReservations.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

  @Mock
  private FlightRepository flightRepository;
  @Mock
  private AirportRepository airportRepository;
  private FlightService underTest;

  @BeforeEach
  void setUp() {
    underTest = new FlightService(flightRepository, airportRepository);
  }

  @Test
  void should_Get_All_Flights() {
    underTest.getFlights();
    verify(flightRepository).findAll();
  }

  @Test
  void should_Get_Flight_By_Id_If_Exists() {
    Airport originAirport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport(UUID.randomUUID(), "RIX", "Riga", "Latvia");
//    airportRepository.save(originAirport);
//    airportRepository.save(destinationAirport);

    Flight flight = new Flight(UUID.randomUUID(), originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);

    UUID id = flight.getId();
    given(flightRepository.findById(id)).willReturn(Optional.of(flight));
    underTest.getFlightById(id);
    verify(flightRepository).findById(id);
  }

  @Test
  void should_Throw_If_Flight_Does_Not_Exist() {
    UUID id = UUID.randomUUID();
    given(flightRepository.findById(id)).willReturn(Optional.empty());
    assertThatThrownBy(() -> underTest.getFlightById(id)).isInstanceOf(
        FlightNotFoundException.class);
  }

  @Test
  void should_Delete_Flight_If_Exists() {
    UUID id = UUID.randomUUID();
    given(flightRepository.existsById(id)).willReturn(true);
    underTest.deleteFlight(id);
    verify(flightRepository).deleteById(id);
  }

  @Test
  void should_Throw_If_FlightToDelete_Does_Not_Exist() {
    UUID id = UUID.randomUUID();
    given(flightRepository.existsById(id)).willReturn(false);

    assertThatThrownBy(() -> underTest.deleteFlight(id)).isInstanceOf(
        FlightNotFoundException.class);
    verify(flightRepository, never()).deleteById(id);
  }

  @Test
  @Disabled
  void updateFlight() {
  }

  @Test
  void should_Get_Flights_By_OriginAirportId_If_Exists() {
    Airport originAirport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport(UUID.randomUUID(), "RIX", "Riga", "Latvia");
    Flight flight = new Flight(UUID.randomUUID(), originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);

    given(airportRepository.findById(originAirport.getId())).willReturn(Optional.of(originAirport));
    Airport airport = airportRepository.findById(originAirport.getId()).get();

    given(flightRepository.findByOriginAirport(airport)).willReturn(List.of(flight));
    underTest.getFlightsByOriginAirportId(flight.getOriginAirport().getId());
    verify(flightRepository).findByOriginAirport(originAirport);
  }

  @Test
  void should_Throw_When_Flight_ByOriginAirportId_DoesNot_Exist() {
    Airport originAirport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport(UUID.randomUUID(), "RIX", "Riga", "Latvia");
    Flight flight = new Flight(UUID.randomUUID(), originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);

    given(airportRepository.findById(originAirport.getId())).willReturn(Optional.of(originAirport));
    Airport airport = airportRepository.findById(originAirport.getId()).get();

    given(flightRepository.findByOriginAirport(airport)).willReturn(List.of());
    assertThatThrownBy(() -> underTest.getFlightsByOriginAirportId(
        flight.getOriginAirport().getId())).isInstanceOf(
        FlightNotFoundException.class);
  }

  @Test
  void should_Throw_When_OriginAirport_DoesNot_Exist() {

    UUID id = UUID.randomUUID();

    assertThatThrownBy(() -> underTest.getFlightsByOriginAirportId(id)).isInstanceOf(
        AirportNotFoundException.class);
    verify(flightRepository, never()).findByOriginAirport(any());
  }

  @Test
  void should_Get_Flights_By_Origin_And_Destination_AirportId() {
    Airport originAirport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport(UUID.randomUUID(), "RIX", "Riga", "Latvia");
    Flight flight = new Flight(UUID.randomUUID(), originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);

    given(airportRepository.findById(originAirport.getId())).willReturn(Optional.of(originAirport));
    given(airportRepository.findById(destinationAirport.getId())).willReturn(
        Optional.of(destinationAirport));

    given(flightRepository.findFlightsByOriginAirportAndDestinationAirport(originAirport,
        destinationAirport)).willReturn(List.of(flight));

    underTest.getFlightsByOriginAndDestinationAirportId(flight.getOriginAirport().getId(),
        flight.getDestinationAirport().getId());
    verify(flightRepository).findFlightsByOriginAirportAndDestinationAirport(originAirport,
        destinationAirport);

  }

  @Test
  void should_Throw_When_Flight_ByOriginAndDestinationAirportId_DoesNot_Exist() {
    Airport originAirport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport(UUID.randomUUID(), "RIX", "Riga", "Latvia");

    given(airportRepository.findById(originAirport.getId())).willReturn(Optional.of(originAirport));
    given(airportRepository.findById(destinationAirport.getId())).willReturn(
        Optional.of(destinationAirport));

    given(flightRepository.findFlightsByOriginAirportAndDestinationAirport(originAirport,
        destinationAirport)).willReturn(List.of());
    assertThatThrownBy(() -> underTest.getFlightsByOriginAndDestinationAirportId(
        originAirport.getId(), destinationAirport.getId())).isInstanceOf(
        FlightNotFoundException.class);

  }

}