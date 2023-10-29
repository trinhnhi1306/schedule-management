package com.nnptrinh.schedulemanagement.model.model;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrainingScheduleModel {

    @NotBlank(message = "Session name must not be blank")
    private String sessionName;

    @FutureOrPresent(message = "Start time should be future or present")
    private LocalDateTime startTime;

    @Future(message = "End time should be future")
    private LocalDateTime endTime;

    @NotBlank(message = "Training type must not be empty ")
    private String trainingType;

    @NotBlank(message = "Class type must not be empty ")
    private String clazzType;

    @NotBlank(message = "Class details must not be blank")
    private String clazzDetails;

    @NotEmpty(message = "Trainer list must not be empty")
    private List<UserModel> trainers;

    @NotNull (message = "Class must not be empty ")
    private Clazz clazz;

}

