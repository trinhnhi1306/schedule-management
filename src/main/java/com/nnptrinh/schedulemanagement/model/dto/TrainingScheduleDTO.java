package com.nnptrinh.schedulemanagement.model.dto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TrainingScheduleDTO {

    private Long id;

    private String sessionName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String trainingType;

    private String clazzType;

    private String clazzDetails;

    private List<UserDTO> trainers;

    private ClazzDTO clazz;

    private UserDTO organizer;

}

