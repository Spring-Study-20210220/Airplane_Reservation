package com.example.demo.airplane.info.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceRequest {

    @Getter
    @NoArgsConstructor
    public static class Save {
        private String departure;

        private String arrival;

        private LocalDate date;

        private LocalTime time;

        public Save(String departure, String arrival, LocalDate date, LocalTime time) {
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }
    }
}
