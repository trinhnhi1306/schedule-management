package com.nnptrinh.schedulemanagement.controller;

import com.nnptrinh.schedulemanagement.exception.AppUtils;
import com.nnptrinh.schedulemanagement.exception.ResponseObject;
import com.nnptrinh.schedulemanagement.model.dto.AuthResponseDTO;
import com.nnptrinh.schedulemanagement.model.dto.LoginDTO;
import com.nnptrinh.schedulemanagement.model.dto.RegisterDTO;
import com.nnptrinh.schedulemanagement.model.entity.UserEntity;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import com.nnptrinh.schedulemanagement.repository.UserRepository;
import com.nnptrinh.schedulemanagement.security.JwtGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody RegisterDTO registerDTO) {
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return AppUtils.returnJS(HttpStatus.BAD_REQUEST, "Username is taken!", null, false);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setCreatedBy("1");

        ERole userRole = switch (registerDTO.getRole()) {
            case "trainer" -> ERole.TRAINER;
            case "admin" -> ERole.ADMIN;
            default -> ERole.TRAINEE;
        };

        user.setRole(userRole);

        userRepository.save(user);
        return AppUtils.returnJS(HttpStatus.OK, "Register user successfully!", null, true);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return AppUtils.returnJS(HttpStatus.OK, "User logged in successfully!", new AuthResponseDTO(token), true);
    }
}
