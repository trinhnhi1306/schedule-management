package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.dto.TrainingScheduleDTO;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.model.TrainingScheduleModel;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.model.entity.User;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import com.nnptrinh.schedulemanagement.repository.ClazzRepository;
import com.nnptrinh.schedulemanagement.repository.TrainingScheduleRepository;
import com.nnptrinh.schedulemanagement.repository.UserRepository;
import com.nnptrinh.schedulemanagement.service.ITrainingScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TrainingScheduleService implements ITrainingScheduleService {

    @Autowired
    TrainingScheduleRepository trainingScheduleRepository;

    @Autowired
    ClazzRepository clazzRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<TrainingScheduleDTO> getAll() {
        return trainingScheduleRepository.findAll()
                .stream()
                .map(schedule -> mapper.map(schedule, TrainingScheduleDTO.class))
                .toList();
    }

    @Override
    public ResponsePage<TrainingScheduleDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        Page<TrainingSchedule> page = trainingScheduleRepository.findAll(pageable);

        return new ResponsePage<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
                        .stream()
                        .map(schedule -> mapper.map(schedule, TrainingScheduleDTO.class))
                        .toList());
    }

    @Override
    public List<TrainingScheduleDTO> getAllByClazzId(Long clazzId) {
        Optional<Clazz> clazz = clazzRepository.findById(clazzId);
        return trainingScheduleRepository
                .findAllByClazz(clazz.orElseThrow(() -> new EntityNotFoundException("Class does not exist")))
                .stream()
                .map(schedule -> mapper.map(schedule, TrainingScheduleDTO.class))
                .toList();
    }

    @Override
    public TrainingScheduleDTO getById(Long id) {
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        return mapper.map(
                schedule.orElseThrow(() -> new EntityNotFoundException("Schedule is not found")),
                TrainingScheduleDTO.class);
    }

    @Override
    public TrainingScheduleDTO add(TrainingScheduleModel scheduleModel) {
        if (trainingScheduleRepository.existsBySessionName(scheduleModel.getSessionName()))
            throw new IllegalArgumentException("Session name is taken");

        return updateTrainers(
                mapper.map(scheduleModel, TrainingSchedule.class),
                scheduleModel.getTrainerIds());
    }

    @Override
    public TrainingScheduleDTO update(Long id, TrainingScheduleModel scheduleModel) {
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        if (schedule.isPresent()) {
            if (schedule.get().getStartTime().isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("This session was started, you can not update it any more");

            if (!scheduleModel.getSessionName().equals(schedule.get().getSessionName()) && trainingScheduleRepository.existsBySessionName(scheduleModel.getSessionName()))
                throw new IllegalArgumentException("Session name is taken");

            mapper.map(scheduleModel, schedule.get());
            // Clear the old list of trainers
            schedule.get().setTrainers(null);

            if (scheduleModel.getTrainerIds() == null || scheduleModel.getTrainerIds().isEmpty())
                return mapper.map(trainingScheduleRepository.save(schedule.get()), TrainingScheduleDTO.class);
            else
                return updateTrainers(
                        trainingScheduleRepository.save(schedule.get()),
                        scheduleModel.getTrainerIds());
        }
        throw new EntityNotFoundException("Schedule is not found");
    }

    @Override
    public Boolean delete(Long id) {
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        if (schedule.isPresent()) {
            trainingScheduleRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Schedule is not found");
    }

    @Override
    public TrainingScheduleDTO updateTrainers(TrainingSchedule schedule, Set<Long> trainerIds) {
        List<User> updatedTrainers = null;

        if (trainerIds != null && !trainerIds.isEmpty()) {
            // Create new list of trainers
            updatedTrainers = new ArrayList<>();
            for (Long trainerId : trainerIds) {
                Optional<User> optionalTrainer = userRepository.findByIdAndRole(trainerId, ERole.TRAINER);
                optionalTrainer.ifPresent(updatedTrainers::add);
            }
        }

        schedule.setTrainers(updatedTrainers);

        return mapper.map(trainingScheduleRepository.save(schedule), TrainingScheduleDTO.class);
    }
}
