package com.uwc_cam_champion.backend.repositories;

import com.uwc_cam_champion.backend.models.UserModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserModuleRepository extends JpaRepository<UserModule, Long> {
    List<UserModule> findByUserId(Long userId);

    void deleteByUserIdAndModuleInfoId(Long userId, Long moduleInfoId);

}
