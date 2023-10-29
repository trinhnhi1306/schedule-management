package com.nnptrinh.schedulemanagement.model.model;

import com.nnptrinh.schedulemanagement.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String tokenType;
    private String accessToken;
    private UserDTO user;
}
