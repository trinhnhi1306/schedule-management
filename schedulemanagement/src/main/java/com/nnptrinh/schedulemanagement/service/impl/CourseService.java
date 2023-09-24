package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.Course;
import com.nnptrinh.schedulemanagement.repository.CourseRepository;
import com.nnptrinh.schedulemanagement.service.ICourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.orElse(null);
    }

    @Override
    public Course add(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long courseId, Course courseModel) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            mapper.map(courseModel, course.get());
            return courseRepository.save(course.get());
        }
        return null;
    }

    @Override
    public boolean delete(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            courseRepository.deleteById(courseId);
            return true;
        }
        return false;
    }
}
