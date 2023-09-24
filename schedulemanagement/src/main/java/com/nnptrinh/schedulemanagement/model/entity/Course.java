package com.nnptrinh.schedulemanagement.model.entity;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Table(name = "course")
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course extends BaseEntity {

    @NotBlank(message = "Name cannot be blank")
    @NotNull
    @Column(name = "name", length = 200, unique = true)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Clazz> clazzes = new HashSet<>();

}

