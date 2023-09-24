package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.model.entity.Course;

import java.util.List;

public interface IClazzService {
    List<Clazz> getAll();
    Clazz add(Clazz clazz);
    Clazz update(Long clazzId);
    Clazz delete(Long clazzId);
}
