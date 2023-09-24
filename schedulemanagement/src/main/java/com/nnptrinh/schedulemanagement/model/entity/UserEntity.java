package com.nnptrinh.schedulemanagement.model.entity;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Table(name = "users")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends BaseEntity {

    @NotBlank(message = "Username cannot be blank")
    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull(message = "Role cannot be null")
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "trainee_clazz", joinColumns = @JoinColumn(name = "trainee_id"), inverseJoinColumns = @JoinColumn(name = "clazz_id"))
    private Set<Clazz> clazzes;


    @ManyToMany
    @JoinTable(name = "trainer_schedule", joinColumns = @JoinColumn(name = "trainer_id"), inverseJoinColumns = @JoinColumn(name = "training_schedule_id"))
    private Set<TrainingSchedule> trainingSchedules;
}

