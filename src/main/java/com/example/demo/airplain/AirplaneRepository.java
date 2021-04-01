package com.example.demo.airplain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<Airplane,Long> {
    Optional<Airplane> findByName(String name);
}
