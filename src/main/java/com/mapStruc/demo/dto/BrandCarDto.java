package com.mapStruc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandCarDto {

    private Long id;
    private String description;
    private List<Car2Dto> cars = new ArrayList<>();
}