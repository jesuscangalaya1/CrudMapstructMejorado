package com.mapStruc.demo.controller;

import com.mapStruc.demo.dto.BrandCarDto;
import com.mapStruc.demo.service.BrandCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BrandCarController {

    private final BrandCarService brandCarService;

    @GetMapping("/brandsAndCars")
    public List<BrandCarDto> getAllBrandsAndCars() {
        return brandCarService.findAllBrandsAndCars();
    }

    @GetMapping("/brands")
    public List<BrandCarDto> getAllBrands() {
        return brandCarService.findAll();
    }

    @GetMapping("/brands/{id}")
    public BrandCarDto getBrandById(@PathVariable Long id) {
        return brandCarService.findById(id);
    }

    @PostMapping("/brands")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandCarDto createBrand(@RequestBody BrandCarDto brandCarDto) {
        return brandCarService.saveBrandCar(brandCarDto);
    }

    @PutMapping("/updateBrand/{id}")
    public ResponseEntity<BrandCarDto> updatedBrandCar(@PathVariable Long id, @RequestBody BrandCarDto brandCarDto){
        return new ResponseEntity<>(brandCarService.update(id, brandCarDto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBrand/{id}")
    public ResponseEntity<String> deleteBrandCar(@PathVariable Long id){
        brandCarService.deleteById(id);
        return new ResponseEntity<>("Eliminado exitosamente", HttpStatus.OK);
    }
}
