package com.nnptrinh.schedulemanagement.repository;

import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Long> {
    List<TrainingSchedule> findAll();
}

