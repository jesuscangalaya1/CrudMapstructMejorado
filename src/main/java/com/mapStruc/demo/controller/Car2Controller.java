package com.mapStruc.demo.controller;

import com.mapStruc.demo.dto.Car2Dto;
import com.mapStruc.demo.service.Car2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Car2Controller {

    private final Car2Service car2Service;


    @GetMapping("/cars")
    public ResponseEntity<List<Car2Dto>> getAllCars() {
        return new ResponseEntity<>(car2Service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public Car2Dto getCarById(@PathVariable Long id) {
        return car2Service.findById(id);
    }

    @PostMapping("/cars")
    public ResponseEntity<Car2Dto> createCar(@RequestBody Car2Dto car2Dto) {
        return new ResponseEntity<>(car2Service.saveCar2(car2Dto),HttpStatus.CREATED);
    }


    @PutMapping(path = ("/update/{id}"))
    public ResponseEntity<Car2Dto> updatedCar(@PathVariable Long id, @RequestBody Car2Dto car2Dto){
        return new ResponseEntity<>(car2Service.update(id, car2Dto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id ){
        car2Service.delete(id);
        return new ResponseEntity<>("eliminado con exito",HttpStatus.OK);
    }

}

