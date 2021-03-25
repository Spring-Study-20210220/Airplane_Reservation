package com.example.demo.airline.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.demo.airline.Airline;

public class AirlineDto {

    @Getter
    @NoArgsConstructor()
    public static class Request{
        private String name;
        private String country;

        @Builder
        Request(String name, String country) {
            this.name = name;
            this.country = country;
        }

        public Airline toEntity() {
            return Airline.builder()
                    .name(name)
                    .country(country)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor()
    public static class Response{
        private Long id;
        private String name;
        private String country;

        @Builder
        Response(Long id, String name, String country){
            this.id=id;
            this.name=name;
            this.country=country;
        }
    }
}
