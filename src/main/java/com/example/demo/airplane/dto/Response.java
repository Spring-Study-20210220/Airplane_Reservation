package com.example.demo.airplane.dto;

import com.example.demo.airplane.Airplane;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Response {

    @Getter
    @NoArgsConstructor
    public static class SaveDto {
        private Long id;

        public SaveDto(Long id) {
            this.id = id;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateDto {
        private Long id;

        public UpdateDto(Long id) {
            this.id = id;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class AirplaneListDto {

        List<AirplaneDto> airplaneList;

        public AirplaneListDto(List<AirplaneDto> airplaneList){
            this.airplaneList = airplaneList;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class AirplaneDto {
        private Long id;
        private String departure;
        private String arrival;
        private LocalDate date;
        private LocalTime time;

        public AirplaneDto(Long id, String departure, String arrival, LocalDate date, LocalTime time) {
            this.id = id;
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }

        public static Response.AirplaneDto of(Airplane airplane) {
            return new Response.AirplaneDto(
                    airplane.getId(),
                    airplane.getDeparture(),
                    airplane.getArrival(),
                    airplane.getTakeOffDate(),
                    airplane.getTakeOffTime()
            );
        }
    }
}
