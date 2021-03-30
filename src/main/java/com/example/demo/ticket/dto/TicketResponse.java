package com.example.demo.ticket.dto;

import com.example.demo.ticket.SeatClass;
import com.example.demo.ticket.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

public class TicketResponse {

    @Data
    @NoArgsConstructor
    public static class Save {

        private Long id;

        public Save(Long id) {
            this.id = id;
        }
    }

    @Data
    @NoArgsConstructor
    public static class TicketInfo {

        private Long id;

        private SeatClass seatClass;

        private String seatNumber;

        private int price;

        private Long airplaneId;

        public TicketInfo(Long id, SeatClass seatClass, String seatNumber, int price, Long airplaneId) {
            this.id = id;
            this.seatClass = seatClass;
            this.seatNumber = seatNumber;
            this.price = price;
            this.airplaneId = airplaneId;
        }

        public static TicketInfo of(Ticket ticket) {
            return new TicketResponse.TicketInfo(
                    ticket.getId(),
                    ticket.getSeatClass(),
                    ticket.getSeatNumber(),
                    ticket.getPrice(),
                    ticket.getAirplane().getId()
            );
        }
    }

    @Data
    @NoArgsConstructor
    public static class TicketListInfo {

        private List<TicketInfo> list;

        public TicketListInfo(List<TicketInfo> list) {
            this.list = list;
        }
    }

}
