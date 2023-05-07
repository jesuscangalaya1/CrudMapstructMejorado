package com.mapStruc.demo.service;

import com.mapStruc.demo.dto.BrandCarDto;
import com.mapStruc.demo.dto.Car2Dto;
import com.mapStruc.demo.entity.BrandCar;
import com.mapStruc.demo.mapper.BrandCarMapper;
import com.mapStruc.demo.repository.BrandCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandCarService {

    private final BrandCarRepository brandCarRepository;
    private final BrandCarMapper brandCarMapper;
    private final BrandCarMapper car2Mapper;

   public List<BrandCarDto> findAll() {
        List<BrandCar> brandCarList = brandCarRepository.findAll();
        return brandCarMapper.brandCarListToBrandCarDtoList(brandCarList);
    }

    public List<BrandCarDto> findAllBrandsAndCars() {
        List<BrandCar> brandCarList = brandCarRepository.findAll();
        return brandCarList.stream()
                .map(brandCar -> {
                    BrandCarDto brandCarDto = brandCarMapper.brandCarToBrandCarDto(brandCar);
                    List<Car2Dto> car2DtoList = car2Mapper.carsToCarDtos(brandCar.getCars());
                    brandCarDto.setCars(car2DtoList);
                    return brandCarDto;
                })
                .collect(Collectors.toList());
    }




    public BrandCarDto findById(Long id) {
        BrandCar brandCar = brandCarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BrandCar not found with id " + id));
        return brandCarMapper.brandCarToBrandCarDto(brandCar);
    }

    public BrandCarDto saveBrandCar(BrandCarDto brandCarDto) {
        BrandCar brandCar = car2Mapper.brandCarDtoToBrandCar(brandCarDto);
        BrandCar savedBrandCar = brandCarRepository.save(brandCar);
        return car2Mapper.brandCarToBrandCarDto(savedBrandCar);
    }


    public BrandCarDto update(Long id, BrandCarDto brandCarDto) {
        BrandCar brandCar = brandCarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BrandCar not found with id " + id));
        brandCarMapper.updateBrandCarFromDto(brandCarDto, brandCar);
        brandCar = brandCarRepository.save(brandCar);
        return brandCarMapper.brandCarToBrandCarDto(brandCar);
    }

    public void deleteById(Long id) {
        brandCarRepository.deleteById(id);
    }




}
