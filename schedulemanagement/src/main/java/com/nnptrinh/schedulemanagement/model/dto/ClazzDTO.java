package com.nnptrinh.schedulemanagement.model.dto;

import lombok.Data;

@Data
public class ClazzDTO {

    private Long id;

    private String name;

    private String description;

    private CourseDTO course;

}