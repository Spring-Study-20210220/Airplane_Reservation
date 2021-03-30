package airplane.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airplane airplane;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassSeat classSeat;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
}
