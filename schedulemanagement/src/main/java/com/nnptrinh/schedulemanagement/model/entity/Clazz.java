package com.nnptrinh.schedulemanagement.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import com.nnptrinh.schedulemanagement.model.enums.EClassType;
import com.nnptrinh.schedulemanagement.model.enums.ETrainingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "clazz")
@EntityListeners(AuditingEntityListener.class)
public class Clazz extends BaseEntity {

    @NotNull(message = "Training type cannot be null")
    @Column(name = "training_type")
    @Enumerated(EnumType.STRING)
    private ETrainingType trainingType;

    @NotNull(message = "Class type type cannot be null")
    @Column(name = "class_type")
    @Enumerated(EnumType.STRING)
    private EClassType classType;

    @NotBlank(message = "Link cannot be blank")
    @Column(name = "link", length = 500)
    private String link;

    @Column(name = "room_information", length = 500)
    private String roomInformation;

    @Column(name = "passcode", length = 10)
    private String passcode;

    @Column(name = "guideline", length = 500)
    private Date guideline;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "clazz", cascade = CascadeType.ALL)
    private Set<TrainingSchedule> trainingSchedules;

    @ManyToMany(mappedBy = "clazzes")
    private Set<User> trainees;

}

