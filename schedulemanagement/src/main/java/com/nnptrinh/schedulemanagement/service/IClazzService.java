package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.ClazzDTO;
import com.nnptrinh.schedulemanagement.model.model.ClazzModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;

import java.util.List;

public interface IClazzService {
    List<ClazzDTO> getAll();

    ResponsePage<ClazzDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir);

    ClazzDTO getById(Long clazzId);

    ClazzDTO add(ClazzModel clazzModel);

    ClazzDTO update(Long clazzId, ClazzModel clazzModel);

    Boolean delete(Long clazzId);
}
