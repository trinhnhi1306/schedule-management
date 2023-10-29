package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.dto.StreamingMediaDTO;
import com.nnptrinh.schedulemanagement.model.enums.EFileType;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import com.nnptrinh.schedulemanagement.model.model.UserModel;
import com.nnptrinh.schedulemanagement.service.impl.FileService;
import com.nnptrinh.schedulemanagement.service.impl.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of users successfully!", userService.getAll(), true);
    }

    @GetMapping(value = "/trainers")
    public ResponseEntity<ResponseObject> listAllTrainers() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of trainers successfully!", userService.getByRole(ERole.TRAINER), true);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> listPage(
            @RequestParam(name = Constant.PAGE_NUM, defaultValue = Constant.DEFAULT_VALUE_PAGE_NUM) int pageNum,
            @RequestParam(name = Constant.PAGE_SIZE, defaultValue = Constant.DEFAULT_VALUE_PAGE_SIZE) int pageSize,
            @RequestParam(name = Constant.SORT_FIELD, defaultValue = Constant.DEFAULT_VALUE_SORT_FIELD) String sortField,
            @RequestParam(name = Constant.SORT_DIR, defaultValue = Constant.DEFAULT_VALUE_SORT_DIR) String sortDir) {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve page of users successfully!", userService.getPage(pageNum, pageSize, sortField, sortDir), true);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> createNewOne(@Valid @RequestBody UserModel userModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Create user successfully!", userService.add(userModel), true);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Valid @RequestBody UserModel userModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Update user successfully!", userService.update(id, userModel), true);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        return AppUtils.returnJS(HttpStatus.OK, "Delete user successfully!", userService.delete(id), true);
    }

    @GetMapping(value = "/images/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE,
            MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<StreamingResponseBody> getWebFile(
            @PathVariable(value = "fileName") String fileName,
            @RequestHeader(value = "range", required = false) String rangeHeader){
        StreamingMediaDTO resourceDto = fileService.getWebFile(fileName, rangeHeader, EFileType.USER);

        return new ResponseEntity<>(resourceDto.getResponseBody(), resourceDto.getHeaders(), HttpStatus.OK);
    }
}
