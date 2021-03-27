package com.example.demo.airplain;

import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.airplain.seat.Seat;
import com.example.demo.airplain.seat.SeatClass;
import com.example.demo.airplain.seat.SeatInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Seat> seats = new HashSet<Seat>();

    @Builder
    Airplain(String name, LocalDateTime takeOffTime, LocalDateTime landingTime,
             Place takeOff, Place landing, AirplaneType airplaneType) {
        this.name = name;
        this.takeOffTime = takeOffTime;
        this.takeOff = takeOff;
        this.landingTime = landingTime;
        this.landing = landing;
        this.airplaneType = airplaneType;
        generateSeatRows();
    }

    AirplaneDto.Response toResponseDto() {
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

    private void addSeat(Seat seat) {
        seats.add(seat);
        seat.registerAirplain(this);
    }

    private void generateSeatRows() {
        int rows = airplaneType.getNumberOfRow();
        int firstClassRow = airplaneType.getFirstClassRowCnt();
        int businessClassRow = airplaneType.getBusinessClassRowCnt();
        int economyClassRow = rows - firstClassRow - businessClassRow;
        for (int i = 0; i < firstClassRow; i++) {
            generateSeatAlphabets(i + 1, SeatClass.FIRST);
        }
        for (int i = 0; i < businessClassRow; i++) {
            generateSeatAlphabets(i + 1, SeatClass.BUSINESS);
        }
        for (int i = 0; i < economyClassRow; i++) {
            generateSeatAlphabets(i + 1, SeatClass.ECONOMY);
        }
    }

    private void generateSeatAlphabets(int row, SeatClass seatClass) {
        List<String> alphabets = airplaneType.getSeatAlphabets();
        for (String alphabet : alphabets) {
            SeatInfo seatInfo = SeatInfo.builder()
                    .row(row)
                    .alphabet(alphabet)
                    .build();
            Seat seat = Seat.builder()
                    .seatInfo(seatInfo)
                    .seatClass(seatClass)
                    .build();
            addSeat(seat);
        }
    }
}
