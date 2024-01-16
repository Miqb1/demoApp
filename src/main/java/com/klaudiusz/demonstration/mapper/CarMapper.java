package com.klaudiusz.demonstration.mapper;

import com.klaudiusz.demonstration.dto.CarDto;
import com.klaudiusz.demonstration.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CarMapper {

    CarMapper MAPPER = Mappers.getMapper(CarMapper.class);


    List<CarDto> mapListToCarDtoList(List<Car> cars);

    CarDto mapToOptionalCarDto(Optional<Car> car);

    Car mapToCar(CarDto carDto);

    CarDto mapToCarDto(Car car);
}
