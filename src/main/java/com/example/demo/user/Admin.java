package com.example.demo.user;

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
}
