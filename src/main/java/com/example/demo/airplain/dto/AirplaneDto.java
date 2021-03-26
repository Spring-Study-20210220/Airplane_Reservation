package com.example.demo.airplain.dto;

import com.example.demo.airplain.Airplain;
import com.example.demo.airplain.AirplaneType;
import com.example.demo.airplain.Place;
import com.example.demo.airplain.seat.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class AirplaneDto {

    @Getter
    @NoArgsConstructor()
    public static class Request {
        private String name;
        private LocalDateTime takeOffTime;
        private LocalDateTime landingTime;

        private Place takeOff;
        private Place landing;

        private AirplaneType airplaneType;

        @Builder
        Request(String name, LocalDateTime takeOffTime, LocalDateTime landingTime,
                Place takeOff, Place landing, AirplaneType airplaneType) {
            this.name = name;
            this.takeOffTime = takeOffTime;
            this.takeOff = takeOff;
            this.landingTime = landingTime;
            this.landing = landing;
            this.airplaneType = airplaneType;
        }

        public Airplain toEntity() {
            return Airplain.builder()
                    .name(name)
                    .takeOffTime(takeOffTime)
                    .landingTime(landingTime)
                    .takeOff(takeOff)
                    .landing(landing)
                    .airplaneType(airplaneType)
                    .build();
        }
    }

    public static class Response {
        private Long id;
        private String name;

        private LocalDateTime takeOffTime;
        private LocalDateTime landingTime;

        private Place takeOff;
        private Place landing;

        private AirplaneType airplaneType;
        @Builder
        Response(Long id, String name, LocalDateTime takeOffTime, LocalDateTime landingTime,
                 Place takeOff, Place landing, AirplaneType airplaneType) {
            this.id=id;
            this.name = name;
            this.takeOffTime = takeOffTime;
            this.takeOff = takeOff;
            this.landingTime = landingTime;
            this.landing = landing;
            this.airplaneType = airplaneType;
        }
    }

}
