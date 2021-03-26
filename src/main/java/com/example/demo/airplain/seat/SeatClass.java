package com.example.demo.airplain.seat;

import lombok.Getter;

@Getter
public enum SeatClass {
    ECONOMY("이코노미", 100000),
    BUSINESS("비지니스", 150000),
    FIRST("퍼스트", 300000);

    private final String name;
    private final int cost;

    SeatClass(String name, int cost) {
        this.cost = cost;
        this.name = name;
    }
}
