package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.StreamingMediaDTO;
import com.nnptrinh.schedulemanagement.model.enums.EFileType;

import java.io.IOException;

public interface IFileService {
    StreamingMediaDTO getWebFile(String fileName, String rangeHeader, EFileType fileType);
}
