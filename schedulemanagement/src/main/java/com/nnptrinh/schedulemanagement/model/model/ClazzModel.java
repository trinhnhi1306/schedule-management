package com.nnptrinh.schedulemanagement.model.model;
import com.nnptrinh.schedulemanagement.model.entity.Course;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClazzModel {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;

    private Course course;

}

