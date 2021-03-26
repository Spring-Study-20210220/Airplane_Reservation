package com.example.demo.airplane.info.dto;

import com.example.demo.airplane.info.AirplaneInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Response {

    @Getter
    @NoArgsConstructor
    public static class Save {
        private Long id;

        public Save(Long id) {
            this.id = id;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Update {
        private Long id;

        public Update(Long id) {
            this.id = id;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class AirplaneInfoList {

        List<com.example.demo.airplane.info.AirplaneInfo> airplaneInfoList;

        public AirplaneInfoList(List<com.example.demo.airplane.info.AirplaneInfo> airplaneInfoList){
            this.airplaneInfoList = airplaneInfoList;
        }
    }


    @Getter
    @NoArgsConstructor
    public static class AirplaneInfo {
        private Long id;
        private String departure;
        private String arrival;
        private LocalDate date;
        private LocalTime time;

        public AirplaneInfo(Long id, String departure, String arrival, LocalDate date, LocalTime time) {
            this.id = id;
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }

        public static Response.AirplaneInfo of(com.example.demo.airplane.info.AirplaneInfo airplaneInfo) {
            return new Response.AirplaneInfo(
                    airplaneInfo.getId(),
                    airplaneInfo.getDeparture(),
                    airplaneInfo.getArrival(),
                    airplaneInfo.getTakeOffDate(),
                    airplaneInfo.getTakeOffTime()
            );
        }

    }
}
