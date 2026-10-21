package com.microblog.user_service.controller;

import com.microblog.user_service.dto.LoginRequest;
import com.microblog.user_service.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microblog.user_service.dto.UserResponse;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "201", description = "User created")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        UserResponse stub = new UserResponse();
        stub.setId(1L);
        stub.setUsername(request.getUsername());
        stub.setEmail(request.getEmail());
        stub.setDisplayName(request.getDisplayName());
        stub.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(stub);
    }

    @Operation(summary = "Login existing user")
    @ApiResponse(responseCode = "200", description = "Login Successful")
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse stub = new UserResponse();
        return ResponseEntity.status(HttpStatus.OK).body(stub);
    }

    @Operation(summary = "Get the User with id")
    @ApiResponse(responseCode = "200", description = "Return user if exists")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse stub = new UserResponse();
        return ResponseEntity.status(HttpStatus.OK).body(stub);
    }

    @Operation(summary = "Update the user")
    @ApiResponse(responseCode = "200", description = "Update the user if exists")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody RegisterRequest request) {
        UserResponse stub = new UserResponse();
        return ResponseEntity.status(HttpStatus.OK).body(stub);
    }
}
