package com.example.demo.airplain.seat;

import com.example.demo.airplain.Airplain;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat {
    @Id
    @GeneratedValue
    @Column(name = "SEAT_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus=SeatStatus.AVAILABLE;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    @Embedded
    private SeatInfo seatInfo;

    @JoinColumn(name = "AIRPLAIN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airplain airplain;

    @Builder
    Seat(SeatClass seatClass, SeatInfo seatInfo) {
        this.seatClass = seatClass;
        this.seatInfo = seatInfo;
    }

    public void registerAirplain(Airplain airplain){
        this.airplain=airplain;
    }

    public void bookSeat(){
        this.seatStatus=SeatStatus.UNAVAILABLE;
    }
    public void unBookSeat(){
        this.seatStatus=SeatStatus.AVAILABLE;
    }
}
