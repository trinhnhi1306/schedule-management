package com.nnptrinh.schedulemanagement.service.impl;

import com.nnptrinh.schedulemanagement.constant.Constant;
import com.nnptrinh.schedulemanagement.model.dto.UserDTO;
import com.nnptrinh.schedulemanagement.model.model.RegisterRequest;
import com.nnptrinh.schedulemanagement.model.model.ResponsePage;
import com.nnptrinh.schedulemanagement.model.model.UserModel;
import com.nnptrinh.schedulemanagement.model.entity.User;
import com.nnptrinh.schedulemanagement.model.enums.ERole;
import com.nnptrinh.schedulemanagement.repository.UserRepository;
import com.nnptrinh.schedulemanagement.security.CustomUserDetails;
import com.nnptrinh.schedulemanagement.security.SecurityConstant;
import com.nnptrinh.schedulemanagement.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    @Override
    public List<UserDTO> getAll() {
        return Arrays.asList(mapper.map(userRepository.findAll(), UserDTO[].class));
    }

    @Override
    public ResponsePage<UserDTO> getPage(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Page<User> page = userRepository.findAll(PageRequest.of(pageNum - 1, pageSize, sort));

        return new ResponsePage<>(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                Arrays.asList(mapper.map(page.getContent(), UserDTO[].class)));
    }

    @Override
    public UserDTO getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return mapper.map(
                user.orElseThrow(() -> new EntityNotFoundException("User is not found")),
                UserDTO.class);
    }

    @Override
    public List<UserDTO> getByRole(ERole role) {
        System.out.println("List<UserDTO> getByRole(ERole role)");
        return Arrays.asList(mapper.map(userRepository.findAllByRole(role), UserDTO[].class));
    }

    @Override
    public UserDTO add(UserModel userModel) {
        if (userRepository.existsByUsername(userModel.getUsername()))
            throw new IllegalArgumentException("Username is taken");

        User user = mapper.map(userModel, User.class);

        user.setPassword(encoder.encode(SecurityConstant.DEFAULT_PASSWORD));
        user.setAvatar(Constant.DEFAULT);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new IllegalArgumentException("Username is taken");

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setAvatar(Constant.DEFAULT);
        switch (registerRequest.getRole()) {
            case "trainer" -> user.setRole(ERole.TRAINER);
            case "trainee" -> user.setRole(ERole.TRAINEE);
            case "admin" -> user.setRole(ERole.ADMIN);
        }
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO update(Long id, UserModel userModel) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            if (!userModel.getUsername().equals(user.get().getUsername()) && userRepository.existsByUsername(userModel.getUsername()))
                throw new IllegalArgumentException("Username is taken");

            mapper.map(userModel, user.get());
            return mapper.map(userRepository.save(user.get()), UserDTO.class);
        }
        throw new EntityNotFoundException("User is not found");
    }

    @Override
    public Boolean delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        throw new EntityNotFoundException("User is not found");
    }
}
