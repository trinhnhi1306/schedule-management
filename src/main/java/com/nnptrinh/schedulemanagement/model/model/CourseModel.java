package com.nnptrinh.schedulemanagement.model.model;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseModel {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;

}

