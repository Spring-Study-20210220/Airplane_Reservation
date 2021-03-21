package com.example.demo.user;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

    @Builder
    User(String login_id, String password, String name) {
        this.login_id = login_id;
        this.name = name;
        this.password = password;
    }

}
