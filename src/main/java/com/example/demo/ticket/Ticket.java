package com.example.demo.ticket;

import com.example.demo.airplane.Airplane;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    @Column
    private String seatNumber;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "airplane_id")
    private Airplane airplane;

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Ticket(Long id, SeatClass seatClass, String seatNumber, int price, Airplane airplane) {
        this.id = id;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.price = price;
        this.airplane = airplane;
    }
}
