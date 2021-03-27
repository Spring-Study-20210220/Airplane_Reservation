package com.example.demo.airplane.dto;

import com.example.demo.airplane.Airplane;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class Request {

    @Data
    @Builder
    @NoArgsConstructor
    public static class SaveDto {

        @NotBlank
        private String departure;

        @NotBlank
        private String arrival;

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime time;

        public SaveDto(@NotBlank String departure, @NotBlank String arrival, @NotNull LocalDate date, @NotNull LocalTime time) {
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }

        public Airplane toEntity() {
            return Airplane
                    .builder()
                    .departure(this.departure)
                    .arrival(this.arrival)
                    .takeOffDate(this.date)
                    .takeOffTime(this.time)
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    public static class UpdateDto {

        private String departure;

        private String arrival;

        private LocalDate date;

        private LocalTime time;

        public UpdateDto(String departure, String arrival, LocalDate date, LocalTime time) {
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    public static class SearchFormDto {

        private String searchType;

        private String searchValue;

        public SearchFormDto(String searchType, String searchValue) {
            this.searchType = searchType;
            this.searchValue = searchValue;
        }
    }
}
