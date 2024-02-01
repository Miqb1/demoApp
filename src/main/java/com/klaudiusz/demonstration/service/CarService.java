package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.CarDto;
import com.klaudiusz.demonstration.mapper.CarMapper;
import com.klaudiusz.demonstration.model.Car;
import com.klaudiusz.demonstration.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    private static final Logger CarLOGGER = LoggerFactory.getLogger(CarService.class);

    private CarRepository carRepository;

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
            CarLOGGER.warn("Could not find a car for id: {}", id);
            return null;
        }
        return CarMapper.MAPPER.mapToCarDto(car.get());
    }

    public CarDto createCar(final CarDto carDto) {
        final Car car = carRepository.save(CarMapper.MAPPER.mapToCar(carDto));
        return CarMapper.MAPPER.mapToCarDto(car);
    }

    @Transactional
    public int updateCar(final Long id, final CarDto carDto) {
        final Car car = CarMapper.MAPPER.mapToCar(carDto);
        return carRepository.updateCar(id, car.getRegNumber(), car.getColor());
    }

    public void deleteById(final Long id) {
        carRepository.deleteById(id);
    }

    public void deleteAll() {
        carRepository.deleteAll();
    }
}