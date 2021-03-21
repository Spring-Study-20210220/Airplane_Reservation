package com.example.demo.airplane.info;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
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
    @Temporal(TemporalType.DATE)
    private LocalDate takeOffDate;

    @Column
    @Temporal(TemporalType.TIME)
    private LocalTime takeOffTime;

}
