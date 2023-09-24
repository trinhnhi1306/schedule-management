package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.service.impl.ClazzService;
import com.nnptrinh.schedulemanagement.service.impl.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/training-schedules")
public class TrainingScheduleController {

    @Autowired
    TrainingScheduleService trainingScheduleService;

    @GetMapping
    public ResponseEntity<List<TrainingSchedule>> listAll() {
        return ResponseEntity.ok(trainingScheduleService.getAll());
    }
}
