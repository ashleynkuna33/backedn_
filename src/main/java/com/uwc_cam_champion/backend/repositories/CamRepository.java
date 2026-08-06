package com.uwc_cam_champion.backend.repositories;

import com.uwc_cam_champion.backend.models.Cam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamRepository extends JpaRepository<Cam, Long> {
    
}
