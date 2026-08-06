package com.uwc_cam_champion.backend.services.User;

import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.request.user.*;

public interface IUserService {

    User createUser(CreateUserRequest request);
    User updateUser(UpdateUserRequest request, Long userId);
    void deleteUser();

}
