package com.nnptrinh.schedulemanagement.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T> {
    private int pageNum;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private List<T> content;
}