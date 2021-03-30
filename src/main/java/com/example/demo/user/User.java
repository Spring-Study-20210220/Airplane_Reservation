package com.example.demo.user;

import com.example.demo.authority.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer mileage;

    @ManyToOne
    @JoinColumn(name = "authority_id", referencedColumnName = "authority_id")
    private Authority authority;

    public User(String email, String password, Integer mileage) {
        this.email = email;
        this.password = password;
        this.mileage = mileage;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }
}
