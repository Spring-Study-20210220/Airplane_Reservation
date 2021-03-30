package airplane.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int capacity;

    @OneToOne
    private Schedule schedule;

    @OneToMany(mappedBy = "Airplane")
    private List<Seat> seatList;

}
