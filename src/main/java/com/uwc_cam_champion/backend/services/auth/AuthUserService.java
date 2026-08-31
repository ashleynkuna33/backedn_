package com.uwc_cam_champion.backend.services.auth;

import com.uwc_cam_champion.backend.models.User;
import org.springframework.stereotype.Service;

import com.uwc_cam_champion.backend.repositories.UserRepository;

@Service
public class AuthUserService implements IAuthUser {

    private final UserRepository userRepository;

    public AuthUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public User signUp(User user) {
        return userRepository.save(user);
    }

    @Override
    public void forgotPassword(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            return;
        }
        // TODO: generate reset token, send email, etc.
    }
}