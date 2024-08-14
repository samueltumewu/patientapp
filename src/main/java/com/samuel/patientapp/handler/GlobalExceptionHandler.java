package com.samuel.patientapp.handler;

import com.samuel.patientapp.dto.MyApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MyApiResponse> handleException(Exception  e){
        return ResponseEntity.internalServerError().body(new MyApiResponse(
                "9999",
                "internal server error",
                null
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<MyApiResponse> handleException(HttpMessageNotReadableException  e){
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new MyApiResponse(
                "9998",
                "Parse JSON error",
                null
        ));
    }
}
