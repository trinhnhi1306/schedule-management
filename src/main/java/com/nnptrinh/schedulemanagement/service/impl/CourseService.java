package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.dto.CourseDTO;
import com.nnptrinh.schedulemanagement.model.model.CourseModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.entity.Course;
import com.nnptrinh.schedulemanagement.repository.CourseRepository;
import com.nnptrinh.schedulemanagement.service.ICourseService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<CourseDTO> getAll() {
        System.out.println("List<CourseDTO> getAll()");
        return Arrays.asList(mapper.map(courseRepository.findAll(), CourseDTO[].class));
    }

    @Override
    public ResponsePage<CourseDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Page<Course> page = courseRepository.findAll(PageRequest.of(pageNum - 1, pageSize, sort));

        return new ResponsePage<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                Arrays.asList(mapper.map(page.getContent(), CourseDTO[].class)));
    }

    @Override
    public CourseDTO getById(Long id) {
        return mapper.map(
                courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course is not found")),
                CourseDTO.class);
    }

    @Override
    public CourseDTO add(CourseModel courseModel) {
        if (courseRepository.existsByName(courseModel.getName()))
            throw new IllegalArgumentException("Name is taken");

        return mapper.map(
                courseRepository.save(mapper.map(courseModel, Course.class)),
                CourseDTO.class);
    }

    @Override
    public CourseDTO update(Long id, CourseModel courseModel) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            if (!courseModel.getName().equals(course.get().getName()) && courseRepository.existsByName(courseModel.getName()))
                throw new IllegalArgumentException("Name is taken");

            mapper.map(courseModel, course.get());
            return mapper.map(courseRepository.save(course.get()), CourseDTO.class);
        }
        throw new EntityNotFoundException("Course is not found");
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Course is not found");
    }
}
