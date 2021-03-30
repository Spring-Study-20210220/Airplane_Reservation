package com.example.demo.ticket.dto;

import com.example.demo.ticket.SeatClass;
import com.example.demo.ticket.Ticket;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TicketRequest {

    @Data
    @NoArgsConstructor
    @Builder
    public static class Save {

        @NotNull
        private Long airplaneId;

        @NotBlank
        private String seatClass;

        @NotBlank
        private String seatNo;

        @NotNull
        private Integer price;

        public Save(Long airplaneId, String seatClass, String seatNo, Integer price) {
            this.airplaneId = airplaneId;
            this.seatClass = seatClass;
            this.seatNo = seatNo;
            this.price = price;
        }

        public Ticket toEntity(TicketRequest.Save saveDto) {

            return Ticket.builder()
                    .price(saveDto.getPrice())
                    .seatNumber(saveDto.getSeatNo())
                    .seatClass(SeatClass.valueOf(saveDto.getSeatClass()))
                    .build();
        }
    }
}
