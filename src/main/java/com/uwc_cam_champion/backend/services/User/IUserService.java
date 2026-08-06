package com.uwc_cam_champion.backend.services.User;

import com.uwc_cam_champion.backend.request.user.*;

public interface IUserService {

    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(UpdateUserRequest request, Long userId);
    void deleteUser(Long userId);

}
