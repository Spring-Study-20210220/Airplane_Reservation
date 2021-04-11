package com.example.demo.airplain;

import com.example.demo.airline.Airline;
import com.example.demo.airplain.dto.AirplaneDto;
import com.example.demo.airplain.seat.Seat;
import com.example.demo.airplain.seat.SeatClass;
import com.example.demo.airplain.seat.SeatInfo;
import com.example.demo.util.BaseTimeEntity;
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
public class Airplane extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "AIRPLAIN_ID")
    private Long id;

    private String name;
    private LocalDateTime takeOffTime;
    private LocalDateTime landingTime;

    @Enumerated(value = EnumType.STRING)
    private Place takeOff;
    @Enumerated(value = EnumType.STRING)
    private Place landing;

    @Enumerated(EnumType.STRING)
    private AirplaneType airplaneType;

    @OneToMany(mappedBy = "airplain")
    private Set<Seat> seats = new HashSet<>();

    @JoinColumn(name = "AIRLINE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Airline airline;

    @Builder
    Airplane(Long id, String name, LocalDateTime takeOffTime, LocalDateTime landingTime,
             Place takeOff, Place landing, AirplaneType airplaneType, Airline airline) {
        this.id = id;
        this.name = name;
        this.takeOffTime = takeOffTime;
        this.takeOff = takeOff;
        this.landingTime = landingTime;
        this.landing = landing;
        this.airplaneType = airplaneType;
        this.airline = airline;
        generateSeatRows();
    }

    public void registerAirline(Airline airline){
        if(this.airline!=null){
            this.airline.getAirplanes().remove(this);
        }
        this.airline=airline;
        airline.getAirplanes().add(this);
    }

    public AirplaneDto.Response toResponseDto() {
        return AirplaneDto.Response.builder()
                .id(id)
                .name(name)
                .takeOff(takeOff)
                .takeOffTime(takeOffTime)
                .landing(landing)
                .landingTime(landingTime)
                .airplaneType(airplaneType)
                .airline(airline.toResponseDto())
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
