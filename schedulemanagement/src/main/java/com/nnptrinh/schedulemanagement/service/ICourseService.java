package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.entity.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAll();

    Course getById(Long courseId);

    Course add(Course course);

    Course update(Long courseId, Course courseModel);

    boolean delete(Long courseId);

}
