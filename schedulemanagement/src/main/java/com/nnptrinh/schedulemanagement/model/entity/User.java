package com.nnptrinh.schedulemanagement.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Role cannot be null")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "email")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "trainee_clazz", joinColumns = @JoinColumn(name = "trainee_id"), inverseJoinColumns = @JoinColumn(name = "clazz_id"))
    private Set<Clazz> clazzes;


    @ManyToMany
    @JoinTable(name = "trainer_schedule", joinColumns = @JoinColumn(name = "trainer_id"), inverseJoinColumns = @JoinColumn(name = "training_schedule_id"))
    private Set<TrainingSchedule> trainingSchedules;
}

