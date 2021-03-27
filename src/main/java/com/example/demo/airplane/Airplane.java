package com.example.demo.airplane;

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
@Table(name = "airplane")
public class Airplane {

    @Id
    @Column(name = "airplane_id")
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

    @Column
    private int firstClassCount;

    @Column
    private int businessClassCount;

    @Column
    private int economyClassCount;

    public Airplane(Long id, String departure, String arrival, LocalDate takeOffDate, LocalTime takeOffTime,
                    int firstClassCount, int businessClassCount, int economyClassCount) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.takeOffDate = takeOffDate;
        this.takeOffTime = takeOffTime;
        this.firstClassCount = firstClassCount;
        this.businessClassCount = businessClassCount;
        this.economyClassCount = economyClassCount;
    }
}
