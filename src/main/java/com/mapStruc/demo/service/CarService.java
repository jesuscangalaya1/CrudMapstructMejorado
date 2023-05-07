package com.mapStruc.demo.service;

import com.mapStruc.demo.dto.CarDto;
import com.mapStruc.demo.entity.Car;
import com.mapStruc.demo.exception.modelException.BusinessException;
import com.mapStruc.demo.exception.modelException.DuplicateKeyException;
import com.mapStruc.demo.exception.modelException.ResourceNotFoundException;
import com.mapStruc.demo.mapper.CarMapper;
import com.mapStruc.demo.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;


    public List<CarDto> listCar (){
        return carMapper.toCarsDto(carRepository.findAll());
    }

    public Optional<CarDto> getCarById (Long id){
        return Optional.ofNullable(carRepository.findById(id)
                .map(carMapper::toCarDto)
                .orElseThrow(() -> new ResourceNotFoundException("Carro No encontrado con ID: "+ id)));
    }

    public CarDto createCar(CarDto carDto){
        Car car = carMapper.toCar(carDto);
        if (carRepository.existsByName(car.getName())) {
            throw new DuplicateKeyException("El nombre del carro ya existe: " + car.getName()+ " no se encontró en la base de datos.");
        }
        return carMapper.toCarDto(carRepository.save(car));
    }

    public Optional<CarDto> updateCar(Long id, CarDto carDto) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = carMapper.toCar(carDto);
            car.setId(id); // asegurarse de que el objeto Car tenga el id correcto
            if (carRepository.existsByName(car.getName())) {
                throw new DuplicateKeyException("El nombre del carro ya existe: " + car.getName());
            }
            Car updatedCar = carRepository.save(car);
            return Optional.of(carMapper.toCarDto(updatedCar));
        }
        throw new ResourceNotFoundException("El carro con id " + id + " no se encontró en la base de datos.");
    }

    public void deleteCar(Long id){
        if (!carRepository.existsById(id)) {
            throw new BusinessException("P-404",HttpStatus.NOT_FOUND,"El Id del Carro no existe");
        }
        carRepository.deleteById(id);

    }
}
