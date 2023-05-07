package com.mapStruc.demo.mapper;

import com.mapStruc.demo.dto.BrandCarDto;
import com.mapStruc.demo.dto.Car2Dto;
import com.mapStruc.demo.entity.BrandCar;
import com.mapStruc.demo.entity.Car2;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = {Car2Mapper.class})
public interface BrandCarMapper {

    @Mapping(source = "cars", target = "cars", ignore = true)
    BrandCarDto brandCarToBrandCarDto(BrandCar brandCar);

    @InheritInverseConfiguration
    BrandCar brandCarDtoToBrandCar(BrandCarDto brandCarDto);


    //Listar los brands y cars juntos.
    @Named("withBrandCarId")
    @Mapping(source = "brandCar.id", target = "brandCarId")
    Car2Dto carToCarDtoWithBrandCarId(Car2 car);
    @IterableMapping(qualifiedByName = "withBrandCarId")
    List<Car2Dto> carsToCarDtos(List<Car2> cars);


    void updateBrandCarFromDto(BrandCarDto brandCarDto, @MappingTarget BrandCar brandCar);



    // listar solo los brands
    List<BrandCarDto> brandCarListToBrandCarDtoList(List<BrandCar> brandCarList);


}



