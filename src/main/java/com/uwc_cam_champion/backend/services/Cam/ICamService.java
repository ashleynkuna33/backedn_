package com.uwc_cam_champion.backend.services.Cam;

import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.request.cam.CamRequest;

public interface ICamService {

    Cam addCam(Long userId, CamRequest request);

    Cam getCam(Long userId);

    Cam recalculateCam(Long userId);

    Cam updateCam(Long userId, CamRequest request);

    void deleteCam(Long userId);
}