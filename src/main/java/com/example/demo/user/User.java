package com.example.demo.user;


import com.example.demo.user.dto.AuthDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class User {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected String login_id;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = true)
    protected boolean enable;

//    @Builder
//    User(String login_id, String password, String name) {
//        this.login_id = login_id;
//        this.name = name;
//        this.password = password;
//    }
//
    public AuthDto.Response toResponseDto(){
        return AuthDto.Response.builder()
                .id(this.id)
                .login_id(this.login_id)
                .name(this.name)
                .build();
    }

    public void withdraw(){
        this.enable=false;
    }
}
