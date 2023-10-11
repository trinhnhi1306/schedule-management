package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.TrainingScheduleDTO;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.model.TrainingScheduleModel;
import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;

import java.util.List;
import java.util.Set;

public interface ITrainingScheduleService {
    List<TrainingScheduleDTO> getAll();

    ResponsePage<TrainingScheduleDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir);

    List<TrainingScheduleDTO> getAllByClazzId(Long clazzId);

    TrainingScheduleDTO getById(Long id);

    TrainingScheduleDTO add(TrainingScheduleModel scheduleModel);

    TrainingScheduleDTO update(Long id, TrainingScheduleModel scheduleModel);

    Boolean delete(Long id);

    TrainingScheduleDTO updateTrainers(TrainingSchedule schedule, Set<Long> trainerIds);
}
