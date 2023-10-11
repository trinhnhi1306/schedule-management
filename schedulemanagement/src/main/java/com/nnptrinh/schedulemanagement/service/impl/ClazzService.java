package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.dto.ClazzDTO;
import com.nnptrinh.schedulemanagement.model.model.ClazzModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.repository.ClazzRepository;
import com.nnptrinh.schedulemanagement.service.IClazzService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<ClazzDTO> getAll() {
        return clazzRepository.findAll()
                .stream()
                .map(clazz -> mapper.map(clazz, ClazzDTO.class))
                .toList();
    }

    @Override
    public ResponsePage<ClazzDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<Clazz> page = clazzRepository.findAll(pageable);

        return new ResponsePage<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
                        .stream()
                        .map(clazz -> mapper.map(clazz, ClazzDTO.class))
                        .toList());
    }

    @Override
    public ClazzDTO getById(Long clazzId) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        return mapper.map(
                clazz.orElseThrow(() -> new EntityNotFoundException("Class is not found")),
                ClazzDTO.class);
    }

    @Override
    public ClazzDTO add(ClazzModel clazzModel) {
        if(clazzRepository.existsByName(clazzModel.getName()))
            throw new IllegalArgumentException("Name is taken");

        return mapper.map(
                clazzRepository.save(mapper.map(clazzModel, Clazz.class)),
                ClazzDTO.class);
    }

    @Override
    public ClazzDTO update(Long clazzId, ClazzModel clazzModel) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        if (clazz.isPresent()) {
            if(!clazzModel.getName().equals(clazz.get().getName()) && clazzRepository.existsByName(clazzModel.getName()))
                throw new IllegalArgumentException("Name is taken");

            mapper.map(clazzModel, clazz.get());
            return mapper.map(clazzRepository.save(clazz.get()), ClazzDTO.class);
        }
        throw new EntityNotFoundException("Class is not found");
    }

    @Override
    public Boolean delete(Long clazzId) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        if (clazz.isPresent()) {
            clazzRepository.deleteById(clazzId);
            return true;
        }
        throw new EntityNotFoundException("Class is not found");
    }
}
