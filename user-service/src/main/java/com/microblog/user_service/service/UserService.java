package com.microblog.user_service.service;

import com.microblog.user_service.dto.UpdateProfileRequest;
import com.microblog.user_service.entity.User;
import com.microblog.user_service.utility.Constants;
import com.microblog.user_service.utility.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.microblog.user_service.dto.RegisterRequest;
import com.microblog.user_service.dto.UserResponse;
import com.microblog.user_service.repository.UserRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserResponse register(RegisterRequest request) {
        if (!ValidationUtils.isValidEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Email id");
        }

        if (!ValidationUtils.isAlphanumeric(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Username");
        }

        if (!ValidationUtils.isAlphanumeric(request.getDisplayName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Display name");
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already taken");
        }

        if (repository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Id already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setDisplayName(request.getDisplayName());
        user.setBio(request.getBio());

        User saved = repository.save(user);

        return toResponse(saved);
    }

    public UserResponse getUserById(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.USER_DOES_NOT_EXISTS);
        }

        User present = user.get();
        return toResponse(present);
    }

    public UserResponse updateUser(Long id, UpdateProfileRequest request) {
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.USER_DOES_NOT_EXISTS);
        }

        User present = user.get();
        if (repository.existsByUsername(request.getUsername())
                && !present.getUsername().equals(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.USER_NAME_TAKEN);
        }

        present.setUsername(request.getUsername());
        present.setDisplayName(request.getDisplayName());
        present.setBio(request.getBio());

        User saved = repository.save(present);
        return toResponse(saved);
    }

    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.USER_DOES_NOT_EXISTS);
        }
        repository.deleteById(id);
    }

    private UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setBio(user.getBio());
        response.setDisplayName(user.getDisplayName());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
