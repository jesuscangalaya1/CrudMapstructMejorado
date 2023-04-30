package com.mapStruc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {

    private Long id;
    private String name;
    private String matricula;
    private String precio;
}
