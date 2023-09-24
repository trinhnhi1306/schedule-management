package com.nnptrinh.schedulemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppUtils {

    public static ResponseEntity<ResponseObject> returnJS(HttpStatus httpStatus, String message, Object object, boolean success) {

        return ResponseEntity.status(httpStatus)
                .body(new ResponseObject(message, object, success));
    }

}
