package com.uwc_cam_champion.backend.controllers;

import com.uwc_cam_champion.backend.request.user.CreateUserRequest;
import com.uwc_cam_champion.backend.request.user.UpdateUserRequest;
import com.uwc_cam_champion.backend.request.user.UserResponse;
import com.uwc_cam_champion.backend.services.User.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        System.out.println("Received request to create user: " + request);
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        System.out.println("Received request to update user with id: " + userId + " -> " + request);
        return userService.updateUser(request, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        System.out.println("Received request to delete user with id: " + userId);
        userService.deleteUser(userId);
    }
}
