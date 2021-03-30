package airplane.domain;

import airplane.dto.UserJoinDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Airline airline;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int mileage;

    @OneToMany(mappedBy = "User")
    private List<Reservation> reservationList = new ArrayList<>();

    @Builder(builderMethodName = "defaultBuilder", builderClassName = "defaultBuilder")
    public User(Airline airline, User user) {
        this.airline = airline;
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public User(UserJoinDTO userJoinDTO) {
        this.email = userJoinDTO.getEmail();
        this.password = userJoinDTO.getPassword();
    }
}
