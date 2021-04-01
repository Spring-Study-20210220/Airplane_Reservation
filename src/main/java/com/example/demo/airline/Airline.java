package com.example.demo.airline;

import com.example.demo.airline.dto.AirlineDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airline {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String country;

    @Builder
    Airline(String name, String country) {
        this.name = name;
        this.country = country;
    }

    AirlineDto.Response toResponseDto(){
        return AirlineDto.Response.builder()
                .id(id)
                .name(name)
                .country(country)
                .build();
    }

    public void update(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
