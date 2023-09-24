package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.repository.ClazzRepository;
import com.nnptrinh.schedulemanagement.service.IClazzService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClazzService implements IClazzService {

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<Clazz> getAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Clazz getById(Long clazzId) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        return clazz.orElse(null);
    }

    @Override
    public Clazz add(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public Clazz update(Long clazzId, Clazz clazzModel) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        if (clazz.isPresent()) {
            mapper.map(clazzModel, clazz.get());
            return clazzRepository.save(clazz.get());
        }
        return null;
    }

    @Override
    public boolean delete(Long clazzId) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        if (clazz.isPresent()) {
            clazzRepository.deleteById(clazzId);
            return true;
        }
        return false;
    }
}
