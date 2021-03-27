package com.example.demo.airplain;

import lombok.Getter;

import java.util.List;

@Getter
public enum AirplaneType {
    A220("Airbus", 140, 28, 1, 8,
            List.of("A", "B", "C", "D", "E")),
    A380("Airbus", 270, 30, 2, 10,
            List.of("A", "B", "C", "D", "E", "F", "G", "H", "I")),
    B737("Boeing", 150, 30, 3, 12,
            List.of("A", "B", "C", "D", "E")),
    B747("Boeing", 360, 30, 3, 15,
            List.of("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"));

    private final String manufacturer;
    private final int numberOfSeats;

    private final int numberOfRow;
    private final List<String> seatAlphabets;
    private final int firstClassRowCnt;
    private final int businessClassRowCnt;

    AirplaneType(String manufacturer, int numberOfSeats, int numberOfRow, int firstClassRowCnt,
                 int businessClassRowCnt, List<String> seatAlphabets) {
        this.manufacturer = manufacturer;
        this.numberOfSeats = numberOfSeats;
        this.numberOfRow = numberOfRow;
        this.seatAlphabets = seatAlphabets;
        this.firstClassRowCnt = firstClassRowCnt;
        this.businessClassRowCnt = businessClassRowCnt;
    }

}
