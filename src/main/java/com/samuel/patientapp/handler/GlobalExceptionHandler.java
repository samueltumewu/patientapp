package com.samuel.patientapp.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(Exception  e){
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("data", null);
        respMap.put("message", e.getLocalizedMessage());
        return ResponseEntity.internalServerError().body(respMap);
    }
}
