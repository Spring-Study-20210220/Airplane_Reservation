package com.example.demo.airplane.info;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class AirplaneInfo {

    @Id
    @Column(name = "airplaneinfo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String departure;

    @Column
    private String arrival;

    @Column
    private LocalDate takeOffDate;

    @Column
    private LocalTime takeOffTime;

    public AirplaneInfo(Long id, String departure, String arrival, LocalDate takeOffDate, LocalTime takeOffTime) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.takeOffDate = takeOffDate;
        this.takeOffTime = takeOffTime;
    }
}
