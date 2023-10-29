package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.dto.ClazzDTO;
import com.nnptrinh.schedulemanagement.model.dto.TrainingScheduleDTO;
import com.nnptrinh.schedulemanagement.model.model.ClazzModel;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.repository.ClazzRepository;
import com.nnptrinh.schedulemanagement.service.IClazzService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClazzService implements IClazzService {

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    @Cacheable(value = "clazzes")
    public List<ClazzDTO> getAll() {
        System.out.println("List<ClazzDTO> getAll()");
        return Arrays.asList(mapper.map(clazzRepository.findAll(), ClazzDTO[].class));
    }

    @Override
    public ResponsePage<ClazzDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Page<Clazz> page = clazzRepository.findAll(PageRequest.of(pageNum - 1, pageSize, sort));

        return new ResponsePage<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                Arrays.asList(mapper.map(page.getContent(), ClazzDTO[].class)));
    }

    @Override
    public ClazzDTO getById(Long id) {
        return mapper.map(
                clazzRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Class is not found")),
                ClazzDTO.class);
    }

    @Override
    public ClazzDTO add(ClazzModel clazzModel) {
        if (clazzRepository.existsByName(clazzModel.getName()))
            throw new IllegalArgumentException("Name is taken");

        return mapper.map(
                clazzRepository.save(mapper.map(clazzModel, Clazz.class)),
                ClazzDTO.class);
    }

    @Override
    public ClazzDTO update(Long id, ClazzModel clazzModel) {
        Optional<Clazz> clazz = clazzRepository.findById(id);
        if (clazz.isPresent()) {
            if (!clazzModel.getName().equals(clazz.get().getName()) && clazzRepository.existsByName(clazzModel.getName()))
                throw new IllegalArgumentException("Name is taken");

            mapper.map(clazzModel, clazz.get());
            return mapper.map(clazzRepository.save(clazz.get()), ClazzDTO.class);
        }
        throw new EntityNotFoundException("Class is not found");
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Clazz> clazz = clazzRepository.findById(id);
        if (clazz.isPresent()) {
            clazzRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Class is not found");
    }
}
