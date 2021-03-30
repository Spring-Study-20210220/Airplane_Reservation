package com.example.demo.user.dto;

import com.example.demo.authority.Authority;
import com.example.demo.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {

    @Data
    @NoArgsConstructor
    public static class UserInfo {

        private Long id;

        private Authority.AuthorityRole role;

        private int mileage;

        public UserInfo(Long id, Authority.AuthorityRole role, int mileage) {
            this.id = id;
            this.role = role;
            this.mileage = mileage;
        }

        public static UserResponse.UserInfo of(User user) {
            return new UserInfo(
                    user.getId(),
                    user.getAuthority().getRole(),
                    user.getMileage()
            );
        }
    }

    @Data
    @NoArgsConstructor
    public static class Save {

        private Long id;

        public Save(Long id) {
            this.id = id;
        }
    }
}