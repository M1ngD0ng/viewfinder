package com.sparta.viewfinder.exception.exception;

import com.sparta.viewfinder.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MismatchException extends RuntimeException {
    private final ErrorCode errorCode;
}