package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.CourseDTO;
import com.nnptrinh.schedulemanagement.model.model.CourseModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAll();

    ResponsePage<CourseDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir);

    CourseDTO getById(Long courseId);

    CourseDTO add(CourseModel courseModel);

    CourseDTO update(Long courseId, CourseModel courseModel);

    Boolean delete(Long courseId);

}
