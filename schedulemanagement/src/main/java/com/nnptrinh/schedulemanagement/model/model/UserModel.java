package com.nnptrinh.schedulemanagement.model.model;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    private String email;

    private String firstName;

    private String lastName;

}
