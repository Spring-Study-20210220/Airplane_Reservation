package com.example.demo.domain;

import com.example.demo.airplain.Airplane;
import com.example.demo.airplain.AirplaneType;
import com.example.demo.airplain.Place;

import com.example.demo.airplain.seat.SeatClass;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class AirplainTest {
    private static final String TEST_AIRPLANE_NAME = "testName";
    private static final LocalDateTime TEST_TAKEOFFTIME_NAME = LocalDateTime.of(2021,3,26,12,0);
    private static final LocalDateTime TEST_LANDINGTIME_NAME = LocalDateTime.of(2021,3,27,12,0);
    private static final Place TEST_TAKEOFF = Place.CANADA;
    private static final Place TEST_LANDING = Place.RUSSIA;
    private static final AirplaneType TEST_TYPE = AirplaneType.A220;

    @Test
    void 항공기생성() {
        Airplane airplain = Airplane.builder()
                .name(TEST_AIRPLANE_NAME)
                .takeOffTime(TEST_TAKEOFFTIME_NAME)
                .landingTime(TEST_LANDINGTIME_NAME)
                .takeOff(TEST_TAKEOFF)
                .landing(TEST_LANDING)
                .airplaneType(TEST_TYPE)
                .build();

        assertThat(airplain.getSeats().size()).isEqualTo(140);
        assertThat(airplain.getSeats().stream()
                .map(seat->seat.getSeatClass()).collect(Collectors.toList())
        ).containsAll(Collections.nCopies(5,SeatClass.FIRST));
        assertThat(airplain.getSeats().stream()
                .map(seat->seat.getSeatClass()).collect(Collectors.toList())
        ).containsAll(Collections.nCopies(40,SeatClass.BUSINESS));
        assertThat(airplain.getSeats().stream()
                .map(seat->seat.getSeatClass()).collect(Collectors.toList())
        ).containsAll(Collections.nCopies(95,SeatClass.ECONOMY));
    }


}
