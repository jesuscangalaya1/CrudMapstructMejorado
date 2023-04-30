package com.mapStruc.demo.mapper;

import com.mapStruc.demo.dto.CarDto;
import com.mapStruc.demo.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CarMapper {

        CarDto toCarDto(Car car);

        Car toCar(CarDto carDto);

        List<CarDto> toCarsDto(List<Car> carsList);
}
