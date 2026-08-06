package com.uwc_cam_champion.backend.controllers;

import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.request.user.CreateUserRequest;
import com.uwc_cam_champion.backend.services.User.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody CreateUserRequest request) {
        System.out.println("Received request to create user: " + request);
        return userService.createUser(request);
    }
}
