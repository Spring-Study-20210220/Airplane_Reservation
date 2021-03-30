package com.example.demo.authority;

import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class Authority {

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    @Enumerated(EnumType.STRING)
    private AuthorityRole role;

    public Authority(Long id, AuthorityRole role) {
        this.id = id;
        this.role = role;
    }

    public enum AuthorityRole {
        USER, ADMIN
    }
}
