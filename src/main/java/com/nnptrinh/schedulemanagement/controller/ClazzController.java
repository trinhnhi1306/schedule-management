package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.model.ClazzModel;
import com.nnptrinh.schedulemanagement.service.impl.ClazzService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clazzes")
public class ClazzController {

    @Autowired
    ClazzService clazzService;

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseObject> listAll() {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve list of classes successfully!", clazzService.getAll(), true);
    }

    @GetMapping
    public ResponseEntity<ResponseObject> listPage(
            @RequestParam(name = Constant.PAGE_NUM, defaultValue = Constant.DEFAULT_VALUE_PAGE_NUM) int pageNum,
            @RequestParam(name = Constant.PAGE_SIZE, defaultValue = Constant.DEFAULT_VALUE_PAGE_SIZE) int pageSize,
            @RequestParam(name = Constant.SORT_FIELD, defaultValue = Constant.DEFAULT_VALUE_SORT_FIELD) String sortField,
            @RequestParam(name = Constant.SORT_DIR, defaultValue = Constant.DEFAULT_VALUE_SORT_DIR) String sortDir) {
        return AppUtils.returnJS(HttpStatus.OK, "Retrieve page of classes successfully!", clazzService.getPage(pageNum, pageSize, sortField, sortDir), true);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> createNewOne(@Valid @RequestBody ClazzModel clazzModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Create class successfully!", clazzService.add(clazzModel), true);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateOne(@PathVariable Long id, @Valid @RequestBody ClazzModel clazzModel) {
        return AppUtils.returnJS(HttpStatus.OK, "Update class successfully!", clazzService.update(id, clazzModel), true);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteOne(@PathVariable Long id) {
        return AppUtils.returnJS(HttpStatus.OK, "Delete class successfully!", clazzService.delete(id), true);
    }

}
