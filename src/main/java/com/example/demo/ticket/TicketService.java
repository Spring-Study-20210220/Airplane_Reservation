package com.example.demo.ticket;

import com.example.demo.airplane.Airplane;
import com.example.demo.airplane.AirplaneRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AirplaneRepository airplaneRepository;

    public TicketService(TicketRepository ticketRepository, AirplaneRepository airplaneRepository) {
        this.ticketRepository = ticketRepository;
        this.airplaneRepository = airplaneRepository;
    }

    public Long save(Ticket ticket, Long airplaneId) {
        Airplane airplane = airplaneRepository.findById(airplaneId).orElseThrow(IllegalStateException::new);

        if(!canCreateTicket(airplane, ticket.getSeatClass())){
            throw new IllegalStateException("만들 수 있는 티켓 개수를 초과했습니다.");
        }

        ticket.setAirplane(airplane);
        Ticket savedTicket = ticketRepository.save(ticket);

        return savedTicket.getId();
    }

    private boolean canCreateTicket(Airplane airplane, SeatClass seatClass) {
        int ticketCount = ticketRepository.countByAirplaneIdAndSeatClass(airplane.getId(), seatClass);

        switch (seatClass) {
            case ECONOMY:
                return ticketCount < airplane.getEconomyClassCount();
            case BUSINESS:
                return ticketCount < airplane.getBusinessClassCount();
            case FIRST:
                return ticketCount < airplane.getFirstClassCount();
            default:
                throw new IllegalStateException("잘못된 좌석 클래스입니다.");
        }
    }

}
