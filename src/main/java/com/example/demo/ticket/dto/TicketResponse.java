package com.example.demo.ticket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

public class TicketResponse {

    @Data
    @NoArgsConstructor
    public static class Save {

        private Long id;

        public Save(Long id) {
            this.id = id;
        }
    }
}
