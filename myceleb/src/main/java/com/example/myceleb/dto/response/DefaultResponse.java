package com.example.myceleb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class DefaultResponse<T>{
    private int code;
    private String message;
    private T data;
}
