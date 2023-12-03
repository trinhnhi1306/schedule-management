package com.nnptrinh.schedulemanagement.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.nnptrinh.schedulemanagement.model.dto.TrainingScheduleDTO;
import com.nnptrinh.schedulemanagement.model.dto.UserDTO;
import com.nnptrinh.schedulemanagement.model.enums.EClazzType;
import com.nnptrinh.schedulemanagement.model.enums.ETrainingType;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.model.TrainingScheduleModel;
import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.model.entity.User;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import com.nnptrinh.schedulemanagement.model.model.TrainingScheduleSearch;
import com.nnptrinh.schedulemanagement.model.model.UserModel;
import com.nnptrinh.schedulemanagement.repository.ClazzRepository;
import com.nnptrinh.schedulemanagement.repository.TrainingScheduleRepository;
import com.nnptrinh.schedulemanagement.repository.UserRepository;
import com.nnptrinh.schedulemanagement.service.ITrainingScheduleService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static com.nnptrinh.schedulemanagement.service.impl.TrainingScheduleSpecifications.*;

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

    @Autowired
    WebServiceClient webServiceClient;

    public CityResponse getLocation() throws IOException, GeoIp2Exception {
        return webServiceClient.city();
    }

    @Override
    public List<TrainingScheduleDTO> getAll() {
        return Arrays.asList(mapper.map(
                trainingScheduleRepository.findAll(),
                TrainingScheduleDTO[].class));
    }

    @Override
    public ResponsePage<TrainingScheduleDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir) {
        System.out.println("getPage()");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Page<TrainingSchedule> page = trainingScheduleRepository.findAll(PageRequest.of(pageNum - 1, pageSize, sort));

        return getTrainingScheduleDTOResponsePage(page);
    }

    @Override
    @Cacheable(value = "trainingSchedules", key = "'schedule_clazz_' + #clazzId")
    public List<TrainingScheduleDTO> getAllByClazzId(Long clazzId) {
        System.out.println("getAllByClazzId(Long id)");
        return Arrays.asList(mapper.map(
                trainingScheduleRepository.findAllByClazz(
                        clazzRepository
                                .findById(clazzId)
                                .orElseThrow(() -> new EntityNotFoundException("Class does not exist"))),
                TrainingScheduleDTO[].class));
    }

    @Override
    @Cacheable(value = "trainingSchedules", key = "'schedule_' + #id")
    public TrainingScheduleDTO getById(Long id) {
        System.out.println("getById(Long id)");
        return mapper.map(
                trainingScheduleRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Schedule is not found")),
                TrainingScheduleDTO.class);
    }

    @Override
    @CachePut(value = "trainingSchedules", key = "'schedule_' + #result.id")
    public TrainingScheduleDTO add(TrainingScheduleModel scheduleModel) {
        System.out.println("add(TrainingScheduleModel scheduleModel)");
        if (trainingScheduleRepository.existsBySessionName(scheduleModel.getSessionName()))
            throw new IllegalArgumentException("Session name is taken");

        if (scheduleModel.getEndTime().isBefore(scheduleModel.getStartTime()) ||
                scheduleModel.getEndTime().isEqual(scheduleModel.getStartTime()))
            throw new IllegalArgumentException("The end time must be larger than the start time");

        return updateTrainers(
                mapper.map(scheduleModel, TrainingSchedule.class),
                scheduleModel.getTrainers());
    }

    @Override
    @CachePut(value = "trainingSchedules", key = "'schedule_' + #result.id")
    public TrainingScheduleDTO update(Long id, TrainingScheduleModel scheduleModel) {
        System.out.println("update(Long id, TrainingScheduleModel scheduleModel)");
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        if (schedule.isPresent()) {
            if (schedule.get().getStartTime().isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("This session was started, you can not update it any more");

            if (!scheduleModel.getSessionName().equals(schedule.get().getSessionName()) && trainingScheduleRepository.existsBySessionName(scheduleModel.getSessionName()))
                throw new IllegalArgumentException("Session name is taken");

            if (scheduleModel.getEndTime().isBefore(scheduleModel.getStartTime()) ||
                    scheduleModel.getEndTime().isEqual(scheduleModel.getStartTime()))
                throw new IllegalArgumentException("The end time must be larger than the start time");

            // Clear the old list of trainers
            schedule.get().setTrainers(null);

            TrainingSchedule newSchedule = trainingScheduleRepository.save(schedule.get());
            mapper.map(scheduleModel, newSchedule);

            return updateTrainers(
                    newSchedule,
                    scheduleModel.getTrainers());
        }
        throw new EntityNotFoundException("Schedule is not found");
    }

    @Override
    @CacheEvict(value = "trainingSchedules", key = "'schedule_' + #id")
    public Boolean delete(Long id) {
        System.out.println("delete(Long id)");
        Optional<TrainingSchedule> schedule = trainingScheduleRepository.findById(id);
        if (schedule.isPresent()) {
            if (schedule.get().getStartTime().isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("This session was started, you can not delete it");

            trainingScheduleRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("Schedule is not found");
    }

    @Override
    public TrainingScheduleDTO updateTrainers(TrainingSchedule schedule, List<UserModel> trainers) {
        List<User> updatedTrainers = null;

        if (trainers != null && !trainers.isEmpty()) {
            // Create new list of trainers
            updatedTrainers = new ArrayList<>();
            for (UserModel trainer : trainers) {
                userRepository.findByUsernameAndRole(trainer.getUsername(), ERole.TRAINER)
                        .ifPresent(updatedTrainers::add);
            }
        }

        schedule.setTrainers(updatedTrainers);

        return mapper.map(trainingScheduleRepository.save(schedule), TrainingScheduleDTO.class);
    }

    @Override
    public ResponsePage<TrainingScheduleDTO> filterTrainingSchedules(TrainingScheduleSearch scheduleSearch, int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Page<TrainingSchedule> page = trainingScheduleRepository.findAll(getSpecification(scheduleSearch), PageRequest.of(pageNum - 1, pageSize, sort));

        return getTrainingScheduleDTOResponsePage(page);
    }

    @Override
    public List<TrainingScheduleDTO> filterAllTrainingSchedules(TrainingScheduleSearch scheduleSearch) {
        return trainingScheduleRepository
                .findAll(getSpecification(scheduleSearch))
                .stream()
                .map(schedule -> {
                    TrainingScheduleDTO dto = mapper.map(schedule, TrainingScheduleDTO.class);
                    dto.setOrganizer(
                            mapper.map(
                                    userRepository.findById(schedule.getCreatedBy()).orElse(null),
                                    UserDTO.class));
                    return dto;
                })
                .toList();
    }

    private Specification<TrainingSchedule> getSpecification(TrainingScheduleSearch scheduleSearch) {
        return Specification
                .where(scheduleSearch.getSessionName() == null ? null : sessionNameContains(scheduleSearch.getSessionName()))
                .and(scheduleSearch.getTrainingType() == null ? null : trainingTypeContains(ETrainingType.valueOf(scheduleSearch.getTrainingType())))
                .and(scheduleSearch.getClazzType() == null ? null : clazzTypeContains(EClazzType.valueOf(scheduleSearch.getClazzType())))
                .and(scheduleSearch.getClazz() == null ? null : isClazz(scheduleSearch.getClazz()))
                .and(scheduleSearch.getStartTime() == null && scheduleSearch.getEndTime() == null ? null : timeBetween(scheduleSearch.getStartTime(), scheduleSearch.getEndTime()))
                .and(scheduleSearch.getTrainers() == null ? null : trainersIn(scheduleSearch.getTrainers()));
    }


    private ResponsePage<TrainingScheduleDTO> getTrainingScheduleDTOResponsePage(Page<TrainingSchedule> page) {
        return new ResponsePage<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent()
                        .stream()
                        .map(schedule -> {
                            TrainingScheduleDTO dto = mapper.map(schedule, TrainingScheduleDTO.class);
                            dto.setOrganizer(
                                    mapper.map(
                                            userRepository.findById(schedule.getCreatedBy()).orElse(null),
                                            UserDTO.class));
                            return dto;
                        })
                        .toList());
    }
}
