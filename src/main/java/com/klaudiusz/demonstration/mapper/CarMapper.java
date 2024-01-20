package com.klaudiusz.demonstration.mapper;

import com.klaudiusz.demonstration.dto.CarDto;
import com.klaudiusz.demonstration.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper MAPPER = Mappers.getMapper(CarMapper.class);

    List<CarDto> mapListToCarDtoList(List<Car> cars);

    Car mapToCar(CarDto carDto);

    CarDto mapToCarDto(Car car);
}