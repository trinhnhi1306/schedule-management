package com.nnptrinh.schedulemanagement.repository;

import com.nnptrinh.schedulemanagement.model.entity.User;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);

    List<User> findAllByRole(ERole role);

    Optional<User> findByUsernameAndRole(@NotBlank(message = "Username cannot be blank") String username, @NotNull(message = "Role cannot be null") ERole role);

    Boolean existsByUsername(String username);
}

