package com.uwc_cam_champion.backend.services.User;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import com.uwc_cam_champion.backend.exceptions.ResourceNotFoundException;
import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.repositories.UserRepository;
import com.uwc_cam_champion.backend.repositories.CamRepository;
import com.uwc_cam_champion.backend.request.user.*;


@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final CamRepository camRepository;

    public UserService(UserRepository userRepository, CamRepository camRepository) {
        this.userRepository = userRepository;
        this.camRepository = camRepository;
    }

    // checked
    @Override
    public UserResponse createUser(CreateUserRequest request) {
        try {
            User user = createUserHelper(request);
            User savedUser = userRepository.save(user);

            Cam cam = new Cam(savedUser, BigDecimal.ZERO, new BigDecimal("50.00"), BigDecimal.ZERO);
            camRepository.save(cam);
            savedUser.setCam(cam);

            return mapToResponse(savedUser);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Failed to create user: " + ex.getMessage());
        }
    }

    private User createUserHelper(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    // checked
    @Override
    public UserResponse updateUser(UpdateUserRequest request, Long userId) {
        try {
            User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            user = updateUserHelper(user, request);

            return mapToResponse(userRepository.save(user));
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Failed to update user: " + ex.getMessage());
        }

    }

    private User updateUserHelper(User user, UpdateUserRequest request) {
        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getSurname() != null) {
            user.setSurname(request.getSurname());
        }
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getIsEmailVerified() != null) {
            user.setEmailVerified(request.getIsEmailVerified());
        }
        if (request.getLastLogin() != null) {
            user.setLastLogin(request.getLastLogin());
        }
        if (request.getCam() != null) {
            user.setCam(request.getCam());
        }
        if (request.getDeadlines() != null) {
            user.setDeadlines(request.getDeadlines());
        }
        if (request.getUserModules() != null) {
            user.setUserModules(request.getUserModules());
        }
        // incomplete: add other fields as necessary
        return user;
    }

    // checked
    @Override
    public void deleteUser(Long userId) {
        try {
            User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
            userRepository.delete(user);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Failed to delete user: " + ex.getMessage());
        }
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setIsEmailVerified(user.getEmailVerified());
        response.setPhone(user.getPhone());
        response.setActive(user.getActive());
        return response;
    }
}
