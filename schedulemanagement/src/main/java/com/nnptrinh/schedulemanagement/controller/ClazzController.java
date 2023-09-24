package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.entity.Clazz;
import com.nnptrinh.schedulemanagement.model.entity.Course;
import com.nnptrinh.schedulemanagement.model.entity.User;
import com.nnptrinh.schedulemanagement.service.impl.ClazzService;
import com.nnptrinh.schedulemanagement.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clazzes")
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    @GetMapping
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of classes successfully!", clazzService.getAll(), true);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createNewOne(@Validated @RequestBody Clazz clazz, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Create class successfully!", clazzService.add(clazz), true);
    }
}
