package com.uwc_cam_champion.backend.services.UserModule;

import java.util.List;
import com.uwc_cam_champion.backend.models.UserModule;

public interface IUserModule {

    List<UserModule> getAllModules(Long userId);
    UserModule joinModule(Long userId, Long moduleId);
    void leaveModule(Long userId, Long moduleId);
    UserModule getModuleInfo(Long userId , Long moduleId);
    
}
