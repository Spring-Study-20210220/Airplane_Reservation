package com.example.demo.domain;

import com.example.demo.airline.Airline;
import com.example.demo.airplain.Airplane;
import com.example.demo.airplain.AirplaneType;
import com.example.demo.airplain.Place;
import com.example.demo.airplain.seat.SeatClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AirlineTest {
    private static final String TEST_AIRPLANE_NAME = "testName";
    private static final LocalDateTime TEST_TAKEOFFTIME_NAME = LocalDateTime.of(2021, 3, 26, 12, 0);
    private static final LocalDateTime TEST_LANDINGTIME_NAME = LocalDateTime.of(2021, 3, 27, 12, 0);
    private static final String TEST_AIRLINE_NAME = "testname";
    private static final String TEST_AIRLINE_COUNTRY = "testcountry";
    private static final Place TEST_TAKEOFF = Place.CANADA;
    private static final Place TEST_LANDING = Place.RUSSIA;
    private static final AirplaneType TEST_TYPE = AirplaneType.A220;

    @BeforeAll
    void 항공사초기화() {
        airplaneList = new LinkedList<>();
        airline = Airline.builder()
                .name(TEST_AIRLINE_NAME)
                .country(TEST_AIRLINE_COUNTRY)
                .build();
        for (int i = 1; i < 10; i++) {
            Airplane EachAirplane = Airplane.builder()
                    .id((long) i)
                    .name(TEST_AIRPLANE_NAME + i)
                    .takeOffTime(TEST_TAKEOFFTIME_NAME)
                    .landingTime(TEST_LANDINGTIME_NAME)
                    .takeOff(TEST_TAKEOFF)
                    .landing(TEST_LANDING)
                    .airplaneType(TEST_TYPE)
                    .build();

            airplaneList.add(EachAirplane);
            EachAirplane.registerAirline(airline);
        }
    }

    public Airline airline;
    private LinkedList airplaneList;

    @Test
    void 항공기조회() {
        Airplane airplane = airline.findAirplaneById(1L);
        assertThat(airplane.getName()).isEqualTo(TEST_AIRPLANE_NAME + 1);
    }
}
