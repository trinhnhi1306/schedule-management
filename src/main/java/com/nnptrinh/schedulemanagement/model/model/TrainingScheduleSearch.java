package com.nnptrinh.schedulemanagement.model.model;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrainingScheduleSearch {

    private String sessionName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String trainingType;

    private String clazzType;

    private List<UserModel> trainers;

    private Clazz clazz;

}