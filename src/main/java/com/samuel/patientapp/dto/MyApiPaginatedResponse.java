package com.samuel.patientapp.dto;

public class MyApiPaginatedResponse<T> extends MyApiResponse<PaginatedDataDTO<T>> {
    public MyApiPaginatedResponse(String code, String message, PaginatedDataDTO<T> data) {
        super(code, message, data);
    }

}