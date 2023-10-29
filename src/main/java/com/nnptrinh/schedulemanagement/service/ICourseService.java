package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.CourseDTO;
import com.nnptrinh.schedulemanagement.model.model.CourseModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAll();

    ResponsePage<CourseDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir);

    CourseDTO getById(Long id);

    CourseDTO add(CourseModel courseModel);

    CourseDTO update(Long id, CourseModel courseModel);

    Boolean delete(Long id);

}
