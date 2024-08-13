package com.samuel.patientapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PaginatedDataDTO<T> {
    private int page;
    private long count;
    private int totalPage;
    private List<T> content;
}
