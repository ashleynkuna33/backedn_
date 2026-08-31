package com.uwc_cam_champion.backend.controllers;

import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.request.auth.LoginRequest;
import com.uwc_cam_champion.backend.request.user.UserResponse;
import com.uwc_cam_champion.backend.services.auth.IAuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthUser authUserService;

    public AuthController(IAuthUser authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = authUserService.login(loginRequest.getUsername(), loginRequest.getPassword());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setIsEmailVerified(user.getEmailVerified());
        response.setPhone(user.getPhone());
        response.setActive(user.getActive());

        return ResponseEntity.ok(response);
    }
}