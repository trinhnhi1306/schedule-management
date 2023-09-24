package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.repository.TrainingScheduleRepository;
import com.nnptrinh.schedulemanagement.service.ITrainingScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingScheduleService implements ITrainingScheduleService {

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<TrainingSchedule> getAll() {
        return trainingScheduleRepository.findAll();
    }

    @Override
    public TrainingSchedule getById(Long id) {
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        return schedule.orElse(null);
    }

    @Override
    public TrainingSchedule add(TrainingSchedule schedule) {
        return trainingScheduleRepository.save(schedule);
    }

    @Override
    public TrainingSchedule update(Long id, TrainingSchedule scheduleModel) {
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        if (schedule.isPresent()) {
            mapper.map(scheduleModel, schedule.get());
            return trainingScheduleRepository.save(schedule.get());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        if (schedule.isPresent()) {
            trainingScheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
