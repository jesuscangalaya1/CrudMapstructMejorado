package com.mapStruc.demo.service;

import com.mapStruc.demo.dto.Car2Dto;
import com.mapStruc.demo.entity.BrandCar;
import com.mapStruc.demo.entity.Car2;
import com.mapStruc.demo.mapper.Car2Mapper;
import com.mapStruc.demo.repository.BrandCarRepository;
import com.mapStruc.demo.repository.Car2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class Car2Service {

    private final Car2Repository car2Repository;
    private final BrandCarRepository brandCarRepository;
    private final Car2Mapper car2Mapper;



    public List<Car2Dto> findAll() {
        List<Car2> car2List = car2Repository.findAll();
        return car2List.stream().map(car2Mapper::carToCarDto).collect(Collectors.toList());
    }


    public Car2Dto findById(Long id) {
        Car2 car2 = car2Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car2 not found with id " + id));
        return car2Mapper.carToCarDto(car2);
    }

    public Car2Dto saveCar2(Car2Dto carDto) {
        System.out.println("BrandCarId from carDto: " + carDto.getBrandCarId());
        BrandCar brandCar = brandCarRepository.findById(carDto.getBrandCarId())
                .orElseThrow(() -> new NoSuchElementException("BrandCar not found with id " + carDto.getBrandCarId()));
        System.out.println("BrandCar found: " + brandCar.getDescription());
        Car2 car = car2Mapper.carDtoToCar(carDto);
        car.setBrandCar(brandCar);
        System.out.println("Car with BrandCar set: " + car);
        Car2 savedCar = car2Repository.save(car);
        System.out.println("Saved Car: " + savedCar);
        return car2Mapper.carToCarDto(savedCar);
    }



    public Car2Dto update(Long id, Car2Dto car2Dto) {
        Car2 car2 = car2Repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car2 not found with id " + id));

        car2Mapper.updateCarFromDto(car2Dto, car2);

        car2 = car2Repository.save(car2);
        return car2Mapper.carToCarDto(car2);
    }




    public void delete(Long id) {
        car2Repository.deleteById(id);
    }

}

