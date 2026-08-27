package com.uwc_cam_champion.backend.services.Dashboard;

import org.springframework.stereotype.Service;

import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.repositories.CamRepository;
import com.uwc_cam_champion.backend.request.Dashbaord.DashboardResponse;



@Service
public class DashboardService {
    private final CamRepository camRepository;
    

    public DashboardService(CamRepository camRepository) {
        this.camRepository = camRepository;
    }
    public DashboardResponse getDashboard(Long userId) {
        DashboardResponse response = new DashboardResponse();

        Cam cam = camRepository.findById(userId).orElse(null);
        if (cam != null) {
            response.setActualCam(cam.getActualCam());
        } else{
            response.setActualCam(java.math.BigDecimal.ZERO);
        }
        return response;
    }
    
}
