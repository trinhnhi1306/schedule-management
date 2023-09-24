package com.nnptrinh.schedulemanagement.model.dto;

import com.nnptrinh.schedulemanagement.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private ERole role;

    private String email;

    private String firstName;

    private String lastName;

}
