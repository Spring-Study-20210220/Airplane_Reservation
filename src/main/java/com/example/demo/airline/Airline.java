package com.example.demo.airline;

import com.example.demo.Error.Exception.AirplaneNotFoundException;
import com.example.demo.airline.dto.AirlineDto;
import com.example.demo.airplain.Airplane;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "airline")
    private Set<Airplane> airplains = new HashSet<Airplane>();

    public void registerAirplane(Airplane airplane) {
        airplanes.add(airplane);
        airplane.registerAirline(this);
    }

    @Builder
    Airline(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public AirlineDto.Response toResponseDto() {
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

    public Airplane findAirplaneById(Long airplaneID) {
        return airplanes.stream()
                .filter(airplane -> airplane.getId().equals(airplaneID))
                .findAny().orElseThrow(
                        ()->new AirplaneNotFoundException()
                );
    }
    public Airplane findAirplaneByName(String airplaneName) {
        return airplanes.stream()
                .filter(airplane -> airplane.getName().equals(airplaneName))
                .findAny().orElseThrow(
                        ()->new AirplaneNotFoundException()
                );
    }
}
