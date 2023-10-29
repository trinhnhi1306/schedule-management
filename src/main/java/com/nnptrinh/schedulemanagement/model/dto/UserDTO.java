package com.nnptrinh.schedulemanagement.model.dto;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String avatar;

    private String fullName;

    private String role;

}
