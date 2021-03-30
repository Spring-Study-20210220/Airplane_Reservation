package airplane.repository;

import airplane.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRespository extends JpaRepository<Seat, Long> {
}
