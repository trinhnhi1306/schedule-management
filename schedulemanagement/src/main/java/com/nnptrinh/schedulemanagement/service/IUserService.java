package com.nnptrinh.schedulemanagement.service;

import com.nnptrinh.schedulemanagement.model.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getAll();

    UserEntity getById(Long id);

    UserEntity add(UserEntity userEntity);

    UserEntity update(Long id, UserEntity userEntityModel);

    boolean delete(Long id);
}
