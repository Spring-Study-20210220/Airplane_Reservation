package com.example.demo.airplain;

import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.airplain.seat.Seat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Airplain {

    @Id
    @GeneratedValue
    @Column(name = "AIRPLAIN_ID")
    private Long id;

    private String name;
    private LocalDateTime takeOffTime;
    private LocalDateTime landingTime;

    private Place takeOff;
    private Place landing;

    @Enumerated(EnumType.STRING)
    private AirplaneType airplaneType;

    @OneToMany(mappedBy = "airplain")
    private List<Seat> seats;

    @Builder
    Airplain(String name, LocalDateTime takeOffTime, LocalDateTime landingTime,
             Place takeOff, Place landing, AirplaneType airplaneType) {
        this.name = name;
        this.takeOffTime = takeOffTime;
        this.takeOff = takeOff;
        this.landingTime = landingTime;
        this.landing = landing;
        this.airplaneType = airplaneType;
    }

    AirplaneDto.Response toResponseDto(){
        return AirplaneDto.Response.builder()
                .id(id)
                .name(name)
                .takeOff(takeOff)
                .takeOffTime(takeOffTime)
                .landing(landing)
                .landingTime(landingTime)
                .airplaneType(airplaneType)
                .build();
    }

}
