package com.example.FlightReservations.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.models.Flight;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class FlightRepositoryTest {

  @Autowired
  private FlightRepository underTest;
  @Autowired
  private AirportRepository airportRepository;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void should_Find_Flights_By_OriginAirport() {
    Airport originAirport = new Airport("VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport("RIX", "Riga", "Latvia");
    airportRepository.save(originAirport);
    airportRepository.save(destinationAirport);

    Flight flight = new Flight(originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);
    underTest.save(flight);
    List<Flight> expected = underTest.findByOriginAirport(originAirport);
    assertThat(expected).isEqualTo(List.of(flight));
  }

  @Test
  void should_Return_EmptyList_If_Flights_DoNot_Exist() {
    Airport originAirport = new Airport("VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport("RIX", "Riga", "Latvia");
    airportRepository.save(originAirport);
    airportRepository.save(destinationAirport);

    Flight flight = new Flight(originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);
    underTest.save(flight);
    List<Flight> expected = underTest.findByOriginAirport(destinationAirport);
    assertThat(expected).isEqualTo(List.of());
  }

  @Test
  void should_Find_Flights_By_OriginAirport_And_DestinationAirport() {
    Airport originAirport = new Airport("VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport("RIX", "Riga", "Latvia");
    airportRepository.save(originAirport);
    airportRepository.save(destinationAirport);

    Flight flight = new Flight(originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);
    underTest.save(flight);
    List<Flight> expected = underTest.findFlightsByOriginAirportAndDestinationAirport(originAirport, destinationAirport);
    assertThat(expected).isEqualTo(List.of(flight));
  }

  @Test
  void should_Return_EmptyList_If_Flights_With_OriginAndDestinationAirport_DoNot_Exist() {
    Airport originAirport = new Airport("VNO", "Vilnius", "Lithuania");
    Airport destinationAirport = new Airport("RIX", "Riga", "Latvia");
    Airport randomAirportOne = new Airport("ATH", "Athens", "Greece");
    Airport randomAirportTwo = new Airport("KNO", "Kaunas", "Lithuania");
    airportRepository.save(originAirport);
    airportRepository.save(destinationAirport);
    airportRepository.save(randomAirportOne);
    airportRepository.save(randomAirportTwo);

    Flight flight = new Flight(originAirport, destinationAirport,
        LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);
    underTest.save(flight);
    List<Flight> expected = underTest.findFlightsByOriginAirportAndDestinationAirport(randomAirportOne, randomAirportTwo);
    assertThat(expected).isEqualTo(List.of());

  }


    @Test
  void should_Find_Flights_By_DestinationAirport() {
      Airport originAirport = new Airport("VNO", "Vilnius", "Lithuania");
      Airport destinationAirport = new Airport("RIX", "Riga", "Latvia");
      airportRepository.save(originAirport);
      airportRepository.save(destinationAirport);

      Flight flight = new Flight(originAirport, destinationAirport,
          LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);
      underTest.save(flight);
      List<Flight> expected = underTest.findFlightsByDestinationAirport(destinationAirport);
      assertThat(expected).isEqualTo(List.of(flight));

    }
    @Test
    void should_Return_EmptyList_If_FlightsWith_DestinationAirport_DoNot_Exist() {
      Airport originAirport = new Airport("VNO", "Vilnius", "Lithuania");
      Airport destinationAirport = new Airport("RIX", "Riga", "Latvia");
      airportRepository.save(originAirport);
      airportRepository.save(destinationAirport);

      Flight flight = new Flight(originAirport, destinationAirport,
          LocalDateTime.of(2023, 12, 12, 12, 30), 199.9);
      underTest.save(flight);
      List<Flight> expected = underTest.findFlightsByDestinationAirport(originAirport);
      assertThat(expected).isEqualTo(List.of());
    }
}