package com.example.demo.user.dto;

import com.example.demo.user.Admin;
import com.example.demo.user.Member;
import com.example.demo.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

public class AuthDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request{
        @Length(min = 5,max = 20)
        private String login_id;
        @Length(min = 6, max = 20)
        private String password;
        @Length(min = 2,max = 10)
        private String name;

        @Builder
        Request(String login_id, String password, String name){
            this.login_id = login_id;
            this.password = password;
            this.name = name;
        }

        public Admin toEntity_Admin(){
            return Admin.builder()
                    .login_id(login_id)
                    .password(password)
                    .name(name)
                    .build();
        }

        public Member toEntity_Member(){
            return Member.builder()
                    .login_id(login_id)
                    .password(password)
                    .name(name)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        private Long id;
        private String login_id;
        private String name;

        @Builder
        Response(Long id, String login_id, String name){
            this.id=id;
            this.login_id = login_id;
            this.name = name;
        }
    }
}
