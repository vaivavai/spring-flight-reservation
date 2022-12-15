package com.example.FlightReservations.utils;

import com.example.FlightReservations.models.Airport;
import com.example.FlightReservations.models.AppUser;
import com.example.FlightReservations.models.Flight;
import com.example.FlightReservations.repositories.AppUserRepository;
import com.example.FlightReservations.repositories.FlightRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlightConfig {

  @Bean
  CommandLineRunner commandLineRunner(FlightRepository repository) {
    return args -> {
//      Flight flightOne = new Flight(new Airport("VNO", "Vilnius", "Lithuania"), new Airport("ATH", "Athens", "Greece"), LocalDateTime.of(2023, 8, 12, 12, 30),
//          200.0);
//      Flight flightTwo = new Flight(new Airport("VNO", "Vilnius", "Lithuania"), new Airport("JFK", "New York", "United States"), LocalDateTime.of(2023, 2, 15, 2, 35),
//          600.0);
//      Flight flightThree = new Flight(new Airport("ATH", "Athens", "Greece"), new Airport("VNO", "Vilnius", "Lithuania"), LocalDateTime.of(2023, 2, 20, 15, 0),
//          600.0);
      Flight flightFour = new Flight("New York", "Vilnius", LocalDateTime.of(2023, 3, 15, 10, 30),
          600.0);
      Flight flightFive = new Flight("Vilnius", "Riga", LocalDateTime.of(2023, 3, 24, 17, 55),
          600.0);
      Flight flightSix = new Flight("Riga", "London", LocalDateTime.of(2023, 2, 17, 18, 20),
          600.0);
//      Flight flightSeven = new Flight("London", "Paris", LocalDateTime.of(2023, 2, 4, 1, 0),
//          600);

      repository.saveAll(List.of(flightFour, flightFive, flightSix));
    };
  }

  @Bean
  CommandLineRunner userCommandLineRunner(AppUserRepository repository) {
    return args -> {
      AppUser user1 = new AppUser("Tom", "tom123", "tom@gmail.com", AppUserRole.USER);
      AppUser user2 = new AppUser("Vaiva", "vaiva123", "vaiva@gmail.com", AppUserRole.ADMIN);


      repository.saveAll(List.of(user1, user2));
    };
  }

}
