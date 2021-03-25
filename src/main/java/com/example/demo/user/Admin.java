package com.example.demo.user;

import com.example.demo.user.dto.AuthDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Admin extends User{

    @Builder
    Admin(String login_id, String password, String name) {
        this.login_id = login_id;
        this.name = name;
        this.password = password;
    }

//    AuthDto.Response toResponseDto(){
//        return AuthDto.Response.builder()
//                .id(this.id)
//                .name(this.name)
//                .login_id(this.login_id)
//                .build();
//    }
}
