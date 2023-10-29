package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.ClazzDTO;
import com.nnptrinh.schedulemanagement.model.model.ClazzModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;

import java.util.List;

public interface IClazzService {
    List<ClazzDTO> getAll();

    ResponsePage<ClazzDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir);

    ClazzDTO getById(Long id);

    ClazzDTO add(ClazzModel clazzModel);

    ClazzDTO update(Long id, ClazzModel clazzModel);

    Boolean delete(Long id);
}
