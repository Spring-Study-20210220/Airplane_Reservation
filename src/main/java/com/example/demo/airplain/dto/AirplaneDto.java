package com.example.demo.airplain.dto;

import com.example.demo.airline.Airline;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.airplain.Airplane;
import com.example.demo.airplain.AirplaneType;
import com.example.demo.airplain.Place;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class AirplaneDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        @Length(min = 5,max = 30)
        private String name;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime takeOffTime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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

        public Airplane toEntity() {
            return Airplane.builder()
                    .name(name)
                    .takeOffTime(takeOffTime)
                    .landingTime(landingTime)
                    .takeOff(takeOff)
                    .landing(landing)
                    .airplaneType(airplaneType)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private String name;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime takeOffTime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime landingTime;

        private Place takeOff;
        private Place landing;

        private AirplaneType airplaneType;

        private AirlineDto.Response airline;

        @Builder
        Response(Long id, String name, LocalDateTime takeOffTime, LocalDateTime landingTime,
                 Place takeOff, Place landing, AirplaneType airplaneType, AirlineDto.Response airline) {
            this.id=id;
            this.name = name;
            this.takeOffTime = takeOffTime;
            this.takeOff = takeOff;
            this.landingTime = landingTime;
            this.landing = landing;
            this.airplaneType = airplaneType;
            this.airline = airline;
        }

        public static AirplaneDto.Response of(Airplane airplane,AirlineDto.Response airline){
            return Response.builder()
                    .id(airplane.getId())
                    .name(airplane.getName())
                    .takeOffTime(airplane.getTakeOffTime())
                    .takeOff(airplane.getTakeOff())
                    .landingTime(airplane.getLandingTime())
                    .landing(airplane.getLanding())
                    .airplaneType(airplane.getAirplaneType())
                    .airline(airline)
                    .build();
        }
    }

}
