package com.mapStruc.demo.mapper;

import com.mapStruc.demo.dto.Car2Dto;
import com.mapStruc.demo.entity.BrandCar;
import com.mapStruc.demo.entity.Car2;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface Car2Mapper {

    @Mapping(source = "brandCar.id", target = "brandCarId")
    Car2Dto carToCarDto(Car2 car);

    @Mapping(source = "brandCarId", target = "brandCar.id")
    Car2 carDtoToCar(Car2Dto carDto);




    void updateCarFromDto(Car2Dto carDto, @MappingTarget Car2 car);


    List<Car2Dto> carsToCarDtos(List<Car2> cars);

}
