package com.example.demo.airplain;

import lombok.Getter;

@Getter
public enum AirplaneType {
    A220("Airbus",140),
    A380("Airbus",407),
    B737("Boeing",147),
    B747("Boeing",368);

    private final String manufacturer;
    private final int numberOfSeats;

    AirplaneType(String manufacturer, int numberOfSeats){
        this.manufacturer=manufacturer;
        this.numberOfSeats=numberOfSeats;
    }
}
