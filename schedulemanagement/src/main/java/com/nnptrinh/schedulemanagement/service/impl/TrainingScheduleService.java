package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.repository.TrainingScheduleRepository;
import com.nnptrinh.schedulemanagement.service.ITrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingScheduleService implements ITrainingScheduleService {

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Override
    public List<TrainingSchedule> getAll() {
        return trainingScheduleRepository.findAll();
    }
}
