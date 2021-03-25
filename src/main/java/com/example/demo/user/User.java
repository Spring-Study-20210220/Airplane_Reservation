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

}
