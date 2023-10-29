package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.model.TrainingScheduleModel;
import com.nnptrinh.schedulemanagement.service.impl.TrainingScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/training-schedules")
public class TrainingScheduleController {

    @Autowired
    TrainingScheduleService trainingScheduleService;

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of schedules successfully!", trainingScheduleService.getAll(), true);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> listPage(
            @RequestParam(name = Constant.PAGE_NUM, defaultValue = Constant.DEFAULT_VALUE_PAGE_NUM) int pageNum,
            @RequestParam(name = Constant.PAGE_SIZE, defaultValue = Constant.DEFAULT_VALUE_PAGE_SIZE) int pageSize,
            @RequestParam(name = Constant.SORT_FIELD, defaultValue = Constant.DEFAULT_VALUE_SORT_FIELD) String sortField,
            @RequestParam(name = Constant.SORT_DIR, defaultValue = Constant.DEFAULT_VALUE_SORT_DIR) String sortDir) {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve page of courses successfully!", trainingScheduleService.getPage(pageNum, pageSize, sortField, sortDir), true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable Long id) {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve schedule successfully!", trainingScheduleService.getById(id), true);
    }

    @GetMapping("/clazz/{clazzId}")
    public ResponseEntity<ResponseObject> listAllByClazzId(@PathVariable Long clazzId) {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of schedules successfully!", trainingScheduleService.getAllByClazzId(clazzId), true);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> createNewOne(@Valid @RequestBody TrainingScheduleModel scheduleModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Create schedule successfully!", trainingScheduleService.add(scheduleModel), true);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Valid @RequestBody TrainingScheduleModel scheduleModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Update schedule successfully!", trainingScheduleService.update(id, scheduleModel), true);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        return AppUtils.returnJS(HttpStatus.OK, "Delete schedule successfully!", trainingScheduleService.delete(id), true);
    }

}
