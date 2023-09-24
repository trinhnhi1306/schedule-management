package com.nnptrinh.schedulemanagement.repository;

import com.nnptrinh.schedulemanagement.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);
}

