package com.mapStruc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ErrorDto {

    private LocalDateTime date;
    private String code;
    private String message;
}
