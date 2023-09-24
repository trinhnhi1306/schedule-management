package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.entity.Course;
import com.nnptrinh.schedulemanagement.service.impl.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of courses successfully!", courseService.getAll(), true);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createNewOne(@Validated @RequestBody Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Create course successfully!", courseService.add(course), true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Validated @RequestBody Course course, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Update course successfully!", courseService.update(id, course), true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        if (!courseService.delete(id)) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, "Course is not found!", null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Delete course successfully!", null, true);
    }
}
