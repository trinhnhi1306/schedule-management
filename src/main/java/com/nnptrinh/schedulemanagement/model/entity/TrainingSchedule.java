package com.nnptrinh.schedulemanagement.model.entity;

import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import com.nnptrinh.schedulemanagement.model.enums.EClazzType;
import com.nnptrinh.schedulemanagement.model.enums.ETrainingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "training_schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TrainingSchedule extends BaseEntity {

    @NotBlank(message = "Session name cannot be blank")
    @NotNull(message = "Session name cannot be null")
    @Column(name = "session_name", length = 200, unique = true)
    private String sessionName;

    @FutureOrPresent(message = "Start time should be future or present")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Future(message = "End time should be future")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @NotNull(message = "Training type cannot be null")
    @Column(name = "training_type")
    @Enumerated(EnumType.STRING)
    private ETrainingType trainingType;

    @NotNull(message = "Class type cannot be null")
    @Column(name = "clazz_type")
    @Enumerated(EnumType.STRING)
    private EClazzType clazzType;

    @Column(name = "clazz_details", length = 700)
    private String clazzDetails;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    @ManyToMany
    @JoinTable(name = "trainer_schedule",
            joinColumns = @JoinColumn(name = "training_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id"))
    private List<User> trainers;

}

