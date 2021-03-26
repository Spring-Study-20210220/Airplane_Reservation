package com.example.demo.airplain.seat;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SeatInfo {
    int row;
    String alphabet;

}
