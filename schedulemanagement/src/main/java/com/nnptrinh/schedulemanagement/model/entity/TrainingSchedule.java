package com.nnptrinh.schedulemanagement.model.entity;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.Set;

@Table(name = "training_schedule")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class TrainingSchedule extends BaseEntity {

    @NotBlank(message = "Session name cannot be blank")
    @Column(name = "session_name", length = 200)
    private Date sessionName;

    @FutureOrPresent(message = "Datetime should be future or present")
    @Column(name = "date_time")
    private Date dateTime;

    @Column(name = "duration")
    private Float duration;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    @ManyToMany(mappedBy = "trainingSchedules")
    private Set<User> trainers;

}

