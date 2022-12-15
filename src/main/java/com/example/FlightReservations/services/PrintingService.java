package com.example.FlightReservations.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrintingService {

  public static String formatDate(LocalDateTime ldt) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return ldt.format(formatter);
  }

}
