package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.Course;
import com.nnptrinh.schedulemanagement.repository.CourseRepository;
import com.nnptrinh.schedulemanagement.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course add(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long courseId) {
//        Course course = courseRepository.findById(courseId);
//
//        return courseRepository.save(course);
        return null;
    }

    @Override
    public Course delete(Long courseId) {
        return null;
    }
}
