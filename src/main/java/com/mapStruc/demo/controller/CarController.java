package com.mapStruc.demo.controller;

import com.mapStruc.demo.dto.CarDto;
import com.mapStruc.demo.dto.ResponseDto;
import com.mapStruc.demo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;


    @GetMapping
    public ResponseDto<List<CarDto>> listCars(){
        return new ResponseDto<>("SUCCESS",
                String.valueOf(HttpStatus.OK),
                "CARS SUCCESSFULLY READED",
                carService.listCar());

        //return new ResponseEntity<>(carService.listCar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseDto<Optional<CarDto>> getCarById(@PathVariable Long id){
        return new ResponseDto<>("SUCCESS",
                String.valueOf(HttpStatus.OK),
                "CAR ID: " +id+" SUCCESSFULLY READED",
                carService.getCarById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<CarDto> createCar(@RequestBody CarDto carDto){
        return new ResponseDto<>("SUCCESS",
                String.valueOf(HttpStatus.CREATED),
                "CAR SUCCESSFULLY CREATED",
                carService.createCar(carDto));
    }

    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<Optional<CarDto>> updateCar (@PathVariable Long id,@RequestBody CarDto carDto){
        return new ResponseDto<>("SUCCESS",
                String.valueOf(HttpStatus.OK),
                "CAR ID: "+id+" SUCCESSFULLY UPDATED",
                carService.updateCar(id, carDto));
    }

    @DeleteMapping("/{id}")
    public ResponseDto<String> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return new ResponseDto<>("SUCCESS",
                String.valueOf(HttpStatus.OK),
                "CAR ID: " +id+" SUCCESSFULLY DELETED",
                ""); // Data null.

    }


}
