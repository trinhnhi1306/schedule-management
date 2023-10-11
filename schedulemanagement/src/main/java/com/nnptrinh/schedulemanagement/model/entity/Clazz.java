package com.nnptrinh.schedulemanagement.model.entity;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clazz")
public class Clazz extends BaseEntity {

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Column(name = "name", length = 200, unique = true)
    private String name;

    @Column(name = "description", length = 700)
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "clazz", cascade = CascadeType.ALL)
    private List<TrainingSchedule> trainingSchedules;

    @ManyToMany
    @JoinTable(name = "trainee_clazz", joinColumns = @JoinColumn(name = "clazz_id"), inverseJoinColumns = @JoinColumn(name = "trainee_id"))
    private List<User> trainees;

}