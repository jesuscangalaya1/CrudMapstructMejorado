package com.mapStruc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class ResponseDto<T> implements Serializable {

    private String status;

    private String code;

    private String message;

    private T data;

    public ResponseDto(String status, String code, String message) {
    }
}
