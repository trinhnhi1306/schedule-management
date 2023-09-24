package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.entity.TrainingSchedule;
import com.nnptrinh.schedulemanagement.service.impl.TrainingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/training-schedules")
public class TrainingScheduleController {

    @Autowired
    TrainingScheduleService trainingScheduleService;

    @GetMapping
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of schedules successfully!", trainingScheduleService.getAll(), true);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createNewOne(@Validated @RequestBody TrainingSchedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Create schedule successfully!", trainingScheduleService.add(schedule), true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Validated @RequestBody TrainingSchedule schedule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Update schedule successfully!", trainingScheduleService.update(id, schedule), true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        if (!trainingScheduleService.delete(id)) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, "Schedule is not found!", null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Delete schedule successfully!", null, true);
    }
}
