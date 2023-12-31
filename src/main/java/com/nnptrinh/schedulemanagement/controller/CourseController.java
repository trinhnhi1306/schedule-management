package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.model.CourseModel;
import com.nnptrinh.schedulemanagement.service.impl.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of courses successfully!", courseService.getAll(), true);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> listPage(
            @RequestParam(name = Constant.PAGE_NUM, defaultValue = Constant.DEFAULT_VALUE_PAGE_NUM) int pageNum,
            @RequestParam(name = Constant.PAGE_SIZE, defaultValue = Constant.DEFAULT_VALUE_PAGE_SIZE) int pageSize,
            @RequestParam(name = Constant.SORT_FIELD, defaultValue = Constant.DEFAULT_VALUE_SORT_FIELD) String sortField,
            @RequestParam(name = Constant.SORT_DIR, defaultValue = Constant.DEFAULT_VALUE_SORT_DIR) String sortDir) {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve page of courses successfully!", courseService.getPage(pageNum, pageSize, sortField, sortDir), true);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> createNewOne(@Valid @RequestBody CourseModel courseModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Create course successfully!", courseService.add(courseModel), true);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Valid @RequestBody CourseModel courseModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Update course successfully!", courseService.update(id, courseModel), true);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        return AppUtils.returnJS(HttpStatus.OK, "Delete course successfully!", courseService.delete(id), true);
    }
}
