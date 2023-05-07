package com.mapStruc.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car2Dto {
    private Long id;
    private String name;
    private String matricula;
    private String precio;

    @JsonProperty("brandCarId")
    private Long brandCarId;

}
