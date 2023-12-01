package com.nnptrinh.schedulemanagement.repository;

import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Long>, JpaSpecificationExecutor<TrainingSchedule> {
    List<TrainingSchedule> findAllByClazz(Clazz clazz);
    Boolean existsBySessionName(String sessionName);
}

