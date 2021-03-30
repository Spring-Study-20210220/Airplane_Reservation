package com.example.demo.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    int countByAirplaneIdAndSeatClass(Long airplaneId, SeatClass seatClass);

    List<Ticket> findAllByAirplaneId(Long airplaneId);
}
