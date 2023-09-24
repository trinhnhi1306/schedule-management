package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;

import java.util.List;

public interface ITrainingScheduleService {
    List<TrainingSchedule> getAll();

    TrainingSchedule getById(Long id);

    TrainingSchedule add(TrainingSchedule schedule);

    TrainingSchedule update(Long id, TrainingSchedule scheduleModel);

    boolean delete(Long id);
}
