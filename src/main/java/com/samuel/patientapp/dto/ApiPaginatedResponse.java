package com.samuel.patientapp.dto;

public class ApiPaginatedResponse<T> extends ApiResponse<PaginatedDataDTO<T>>{
    public ApiPaginatedResponse(String code, String message, PaginatedDataDTO<T> data) {
        super(code, message, data);
    }

}