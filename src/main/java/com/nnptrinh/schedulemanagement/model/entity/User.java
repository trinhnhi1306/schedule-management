package com.nnptrinh.schedulemanagement.model.entity;
import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Table(name = "users")
@Data
@Entity
public class User extends BaseEntity {

    @NotBlank(message = "Username cannot be blank")
    @NotNull(message = "Username cannot be null")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
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

    @Column(name = "avatar")
    private String avatar;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @ManyToMany(mappedBy = "trainees")
    private List<Clazz> clazzes;


    @ManyToMany(mappedBy = "trainers")
    private List<TrainingSchedule> trainingSchedules;
}

