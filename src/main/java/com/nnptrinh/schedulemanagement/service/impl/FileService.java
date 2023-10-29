package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.model.dto.StreamingMediaDTO;
import com.nnptrinh.schedulemanagement.model.enums.EFileType;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import com.nnptrinh.schedulemanagement.service.IFileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class FileService implements IFileService {

    @Override
    public StreamingMediaDTO getWebFile(String fileName, String rangeHeader, EFileType fileType) {
        if (!fileName.endsWith(Constant.WEBP_EXTENSION)) {
            fileName += Constant.WEBP_EXTENSION;
        }
        String filePath = "D:\\GroupData\\JavaTrainingProgram\\schedule-management-project\\back-end\\src\\main\\resources\\images\\" + fileType.getValue() + "\\" + fileName;
        log.info("FULL PATH: " + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new EntityNotFoundException("File is not found");
        }
        StreamingResponseBody stream = outputStream -> {
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                // Sử dụng IOUtils.copy để sao chép dữ liệu từ fileInputStream vào outputStream
                IOUtils.copy(fileInputStream, outputStream, 1024);
            } catch (IOException e) {
                //log.error("Exception", e);
            }
        };

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.CONTENT_TYPE, Constant.WEBP_TYPE);
        responseHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
        responseHeaders.setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic());
        return StreamingMediaDTO.builder()
                .headers(responseHeaders)
                .mediaType(Constant.WEBP_TYPE)
                .responseBody(stream)
                .build();
    }
}
