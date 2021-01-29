package com.intelasers.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private int code;
    private String message;

    public BusinessException(Integer errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }


    public BusinessException(String message) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}