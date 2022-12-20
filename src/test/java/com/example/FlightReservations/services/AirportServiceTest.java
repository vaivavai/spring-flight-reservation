package com.example.FlightReservations.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.example.FlightReservations.exceptions.alreadyExists.AirportAlreadyExistsException;
import com.example.FlightReservations.exceptions.notFound.AirportNotFoundException;
import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.repositories.AirportRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

  @Mock
  private AirportRepository airportRepository;
  private AirportService underTest;

  @BeforeEach
  void setUp() {
    underTest = new AirportService(airportRepository);
  }

  @Test
  void should_Get_All_Airports() {
    //when
    underTest.getAirports();
    //then
    verify(airportRepository).findAll();
  }

  @Test
  void should_Get_Airport_By_Id_If_Exists() {
    //given
    Airport airport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    UUID id = airport.getId();
    given(airportRepository.findById(id)).willReturn(Optional.of(airport));
    //when
    //then
    underTest.getAirportById(id);
    verify(airportRepository).findById(id);

  }

  @Test
  void should_Throw_If_Airport_Does_Not_Exist() {
    UUID id = UUID.randomUUID();
    given(airportRepository.findById(id)).willReturn(Optional.empty());
    assertThatThrownBy(() -> underTest.getAirportById(id)).isInstanceOf(
        AirportNotFoundException.class);
  }

  @Test
  void should_Add_New_Airport() {
    Airport airport = new Airport(UUID.randomUUID(), "VNO", "Vilnius", "Lithuania");
    underTest.addNewAirport(airport);
    ArgumentCaptor<Airport> airportArgumentCaptor = ArgumentCaptor.forClass(Airport.class);
    verify(airportRepository).save(airportArgumentCaptor.capture());

    Airport airportArgumentCaptorValue = airportArgumentCaptor.getValue();

    assertThat(airportArgumentCaptorValue).isEqualTo(airport);
  }

  @Test
  void should_Throw_Exception_When_Name_Exists() {
    String name = "VNO";
    Airport airport = new Airport(UUID.randomUUID(), name, "Vilnius", "Lithuania");
    given(airportRepository.findAirportByName(name)).willReturn(Optional.of(airport));

    assertThatThrownBy(() -> underTest.addNewAirport(airport)).isInstanceOf(
        AirportAlreadyExistsException.class);

    verify(airportRepository, never()).save(airport);

  }

  @Test
  void should_Delete_Airport_When_Exists() {
    UUID id = UUID.randomUUID();
    given(airportRepository.existsById(id)).willReturn(true);
    underTest.deleteAirport(id);
    verify(airportRepository).deleteById(id);
  }

  @Test
  void should_NotDelete_When_Airport_Does_Not_Exist() {
    UUID id = UUID.randomUUID();
    given(airportRepository.existsById(id)).willReturn(false);
    assertThatThrownBy(() -> underTest.deleteAirport(id)).isInstanceOf(
        AirportNotFoundException.class);
    verify(airportRepository, never()).deleteById(any());
  }

  @Test
  void should_Update_Airport() {
    Airport airport = new Airport(UUID.randomUUID(), "VLN", "Vilnius", "Lithuania");
    UUID id = airport.getId();
    given(airportRepository.findById(id)).willReturn(Optional.of(airport));
    airport.setName("VNO");
    underTest.updateAirport(id, airport);
    ArgumentCaptor<Airport> airportArgumentCaptor = ArgumentCaptor.forClass(Airport.class);
    verify(airportRepository).save(airportArgumentCaptor.capture());

    Airport airportArgumentCaptorValue = airportArgumentCaptor.getValue();

    assertThat(airportArgumentCaptorValue).isEqualTo(airport);

  }

  @Test
  void update_Airport_Should_Throw() {
    Airport airport = new Airport(UUID.randomUUID(), "VLN", "Vilnius", "Lithuania");

    given(airportRepository.findById(any())).willReturn(Optional.empty());

    assertThatThrownBy(() -> underTest.updateAirport(any(), airport)).isInstanceOf(
        AirportNotFoundException.class);

    verify(airportRepository, never()).save(any());
  }
}