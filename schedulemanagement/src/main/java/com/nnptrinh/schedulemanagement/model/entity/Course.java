package com.nnptrinh.schedulemanagement.model.entity;
import com.nnptrinh.schedulemanagement.model.entity.audit.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Table(name = "course")
@Data
@Entity
public class Course extends BaseEntity {

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @Column(name = "name", length = 200, unique = true)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Clazz> clazzes;

}

