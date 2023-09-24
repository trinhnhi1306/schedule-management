package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.model.entity.UserEntity;
import com.nnptrinh.schedulemanagement.repository.UserRepository;
import com.nnptrinh.schedulemanagement.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    ModelMapper mapper;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(Long id, UserEntity userEntityModel) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            mapper.map(userEntityModel, user.get());
            return userRepository.save(user.get());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
