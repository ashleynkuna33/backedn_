package com.uwc_cam_champion.backend.services.auth;

import com.uwc_cam_champion.backend.models.User;


public interface IAuthUser {
    User login(String username, String password);
    User signUp(User user);
    void forgotPassword(String username);
}
