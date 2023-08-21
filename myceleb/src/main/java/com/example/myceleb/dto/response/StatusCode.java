package com.example.myceleb.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


public class StatusCode {

    /***** 200 ******/
    public static final int OK=200;


    /***** 400 ******/
    public static final int BAD_REQUEST=400;



    /***** 500 ******/

    public static final int INTERNAL_SERVER_ERROR=500;
}
