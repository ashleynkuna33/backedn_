package com.uwc_cam_champion.backend.services.UserModule;

import java.util.List;
import com.uwc_cam_champion.backend.models.ModuleInfo;
import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.models.UserModule;
import com.uwc_cam_champion.backend.repositories.ModuleInfoRepository;
import com.uwc_cam_champion.backend.repositories.UserModuleRepository;
import com.uwc_cam_champion.backend.repositories.UserRepository;

public class UserModuleService implements IUserModule {
    
    private final UserModuleRepository userModuleRepository;
    private final UserRepository userRepository;
    private final ModuleInfoRepository moduleInfoRepository;

    public UserModuleService(UserModuleRepository userModuleRepository, UserRepository userRepository, ModuleInfoRepository moduleInfoRepository) {
        this.userModuleRepository = userModuleRepository;
        this.userRepository = userRepository;
        this.moduleInfoRepository = moduleInfoRepository;
    }

    @Override
    public List<UserModule> getAllModules(Long userId) {
        try {
            return userModuleRepository.findByUserId(userId);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to retrieve modules for user with id: " + userId, ex);
        }
    }

    @Override
    public UserModule joinModule(Long userId, Long moduleId) {
        try {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

            ModuleInfo moduleInfo = moduleInfoRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found with id: " + moduleId));

            UserModule userModule = new UserModule(user, moduleInfo);
            return userModuleRepository.save(userModule);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to join module with id: " + moduleId + " for user with id: " + userId, ex);
        }
    }

    @Override
    public void leaveModule(Long userId, Long moduleId) {
        try {
            userModuleRepository.deleteByUserIdAndModuleInfoId(userId, moduleId);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to leave module with id: " + moduleId + " for user with id: " + userId, ex);
        }
    }

}