package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.dto.UserDTO;
import com.nnptrinh.schedulemanagement.model.model.RegisterRequest;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.model.UserModel;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAll();

    ResponsePage<UserDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir);

    UserDTO getById(Long id);

    UserDTO add(UserModel userModel);

    UserDTO register(RegisterRequest registerRequest);

    UserDTO update(Long id, UserModel userModel);

    Boolean delete(Long id);
}
