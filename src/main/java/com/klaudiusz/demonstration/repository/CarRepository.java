package com.klaudiusz.demonstration.repository;

import com.klaudiusz.demonstration.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Modifying
    @Query("update Car c set c.regNumber = :regNumber, c.color = :color WHERE c.id = :id")
    int updateCar(@Param("id") Long id, @Param("regNumber") String regNumber, @Param("color") String color);
}