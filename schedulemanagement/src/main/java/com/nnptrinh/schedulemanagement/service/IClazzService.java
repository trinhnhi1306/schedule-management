package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.entity.Clazz;

import java.util.List;

public interface IClazzService {
    List<Clazz> getAll();

    Clazz getById(Long clazzId);

    Clazz add(Clazz clazz);

    Clazz update(Long clazzId, Clazz clazzModel);

    boolean delete(Long clazzId);
}
