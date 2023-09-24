package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.entity.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAll();
    Course add(Course course);
    Course update(Long courseId);
    Course delete(Long courseId);

}
