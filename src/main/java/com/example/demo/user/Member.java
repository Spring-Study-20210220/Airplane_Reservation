package com.example.demo.user;

import com.example.demo.user.dto.AuthDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends User{
    @Enumerated(EnumType.STRING)
    private Level level;

    @Builder
    Member(String login_id, String password, String name, Level level) {
        this.login_id = login_id;
        this.name = name;
        this.password = password;
        this.level = level;
    }

//    AuthDto.Response toResponseDto(){
//        return AuthDto.Response.builder()
//                .id(this.id)
//                .name(this.name)
//                .login_id(this.login_id)
//                .build();
//    }
}
