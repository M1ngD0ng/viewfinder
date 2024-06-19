package com.sparta.viewfinder.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private int statusCode;
}