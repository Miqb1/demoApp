package com.klaudiusz.demonstration.controller;

import com.klaudiusz.demonstration.dto.CarDto;
import com.klaudiusz.demonstration.service.CarService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@SuppressWarnings("unused")
public class CarController {
    public static final Logger CarLOGGER = LoggerFactory.getLogger(CarController.class);

    CarService carService;

    @GetMapping("car")
    public ResponseEntity<List<CarDto>> getAllCars() {
        final List<CarDto> list = carService.list();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CarLOGGER.info("Car(s) listed");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "car/{id}")
    public ResponseEntity<CarDto> getOneCarById(@PathVariable final Long id) {
        final CarDto car = carService.findCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("addCars")
    public ResponseEntity<CarDto> createCar(@RequestBody final CarDto newCar) {
        try {
            final CarDto createdCar = carService.createCar(newCar);
            CarLOGGER.info("Car No {} created", createdCar.getId());
            return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
        } catch (final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        }
    }

    @PatchMapping(path = "car/{id}")
    public ResponseEntity<String> updateCar(
            @PathVariable final Long id,
            @RequestBody final CarDto carDto) {

        final int updateCount = carService.updateCar(id, carDto);

        if (updateCount > 0) {
            return ResponseEntity.ok("Car updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with ID " + id + " not found");
        }
    }

    @DeleteMapping("car/all")
    public ResponseEntity<String> deleteAllCars() {
        carService.deleteAll();
        return ResponseEntity.ok("All cars deleted successfully");
    }

    @DeleteMapping("car/{id}")
    public ResponseEntity<String> deleteCarById(@PathVariable final Long id) {
        carService.deleteById(id);
        return ResponseEntity.ok("Car with id " + id + " deleted successfully");
    }
}