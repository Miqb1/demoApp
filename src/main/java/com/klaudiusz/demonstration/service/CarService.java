package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.CarDto;
import com.klaudiusz.demonstration.mapper.CarMapper;
import com.klaudiusz.demonstration.model.Car;
import com.klaudiusz.demonstration.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private static final Logger CarLOGGER = LoggerFactory.getLogger(CarService.class);

    CarRepository carRepository;

    CarService(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDto> list() {
        final List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            CarLOGGER.warn("There are any cars to be listed");
        }
        return CarMapper.MAPPER.mapListToCarDtoList(cars);
    }

    public CarDto findCarById(final Long id) {
        final Optional<Car> car = carRepository.findById(id);
        if (car.isEmpty()) {
            CarLOGGER.warn("Id is empty!");
            return null;
        }
        return CarMapper.MAPPER.mapToCarDto(car.get());
    }

    public CarDto createCar(final CarDto carDto) {
        final Car car = carRepository.save(CarMapper.MAPPER.mapToCar(carDto));
        return CarMapper.MAPPER.mapToCarDto(car);
    }

    public CarDto updateCar(final Long id, final CarDto updatedCar) {
        final Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            final Car existingCar = optionalCar.get();
            if (updatedCar.getColor() != null) {
                existingCar.setColor(updatedCar.getColor());
            }
            if (updatedCar.getRegNumber() != null) {
                existingCar.setRegNumber(updatedCar.getRegNumber());
            }
            carRepository.save(existingCar);
            return CarMapper.MAPPER.mapToCarDto(existingCar);
        }
        return null;
    }

    public void deleteById(final Long id) {
        carRepository.deleteById(id);
    }

    public void deleteAll() {
        carRepository.deleteAll();
    }


}
