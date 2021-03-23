package com.example.demo.airplane.info.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class Response {

    @Getter
    @NoArgsConstructor
    public static class Save {
        private Long id;

        public Save(Long id) {
            this.id = id;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Update {
        private Long id;

        public Update(Long id) {
            this.id = id;
        }
    }
}
