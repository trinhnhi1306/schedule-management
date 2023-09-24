package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.repository.ClazzRepository;
import com.nnptrinh.schedulemanagement.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzService implements IClazzService {

    @Autowired
    ClazzRepository clazzRepository;

    @Override
    public List<Clazz> getAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Clazz add(Clazz clazz) {
        return null;
    }

    @Override
    public Clazz update(Long clazzId) {
        return null;
    }

    @Override
    public Clazz delete(Long clazzId) {
        return null;
    }
}
