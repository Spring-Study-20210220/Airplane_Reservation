package com.example.demo.airplane.info.dto;

import com.example.demo.airplane.info.AirplaneInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class Request {

    @Data
    @Builder
    @NoArgsConstructor
    public static class Save {

        @NotBlank
        private String departure;

        @NotBlank
        private String arrival;

        @NotNull
        private LocalDate date;

        @NotNull
        private LocalTime time;

        public Save(@NotBlank String departure, @NotBlank String arrival, @NotNull LocalDate date, @NotNull LocalTime time) {
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }

        public AirplaneInfo toEntity() {
            return AirplaneInfo
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
    public static class Update {

        private String departure;

        private String arrival;

        private LocalDate date;

        private LocalTime time;

        public Update(String departure, String arrival, LocalDate date, LocalTime time) {
            this.departure = departure;
            this.arrival = arrival;
            this.date = date;
            this.time = time;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    public static class SearchForm {

        private String searchType;

        private String searchValue;

        public SearchForm(String searchType, String searchValue) {
            this.searchType = searchType;
            this.searchValue = searchValue;
        }
    }
}
