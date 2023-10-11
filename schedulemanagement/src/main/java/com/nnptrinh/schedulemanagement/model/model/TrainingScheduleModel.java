package com.nnptrinh.schedulemanagement.model.model;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class TrainingScheduleModel {

    @NotBlank(message = "Session name cannot be blank")
    private String sessionName;

    @FutureOrPresent(message = "Start time should be future or present")
    private LocalDateTime startTime;

    @Future(message = "End time should be future")
    private LocalDateTime endTime;

    @NotBlank(message = "Training type cannot be null")
    private String trainingType;

    @NotBlank(message = "Class type cannot be null")
    private String clazzType;

    private String clazzDetails;

    private Set<Long> trainerIds;

    private Clazz clazz;

}

