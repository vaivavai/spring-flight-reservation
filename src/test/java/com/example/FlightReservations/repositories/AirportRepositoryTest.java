package com.example.FlightReservations.repositories;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.FlightReservations.models.Airport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AirportRepositoryTest {

  @Autowired
  private AirportRepository underTest;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void should_Find_Airport_By_Name() {
    //given
    String airportName = "VNO";
    Airport airport = new Airport(airportName, "Vilnius", "Lithuania");
    underTest.save(airport);
    //when
    Airport expected = underTest.findAirportByName(airportName).get();
    //then
    assertThat(expected).isEqualTo(airport);
  }
}