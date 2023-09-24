package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.entity.UserEntity;
import com.nnptrinh.schedulemanagement.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of users successfully!", userService.getAll(), true);
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createNewOne(@Validated @RequestBody UserEntity userEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Create user successfully!", userService.add(userEntity), true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Validated @RequestBody UserEntity userEntity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage(), null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Update user successfully!", userService.update(id, userEntity), true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        if (!userService.delete(id)) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, "User is not found!", null, false);
        }
        return AppUtils.returnJS(HttpStatus.OK, "Delete user successfully!", null, true);
    }
}
