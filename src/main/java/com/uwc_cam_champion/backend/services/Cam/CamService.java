package com.uwc_cam_champion.backend.services.Cam;

import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.repositories.CamRepository;
import com.uwc_cam_champion.backend.repositories.UserRepository;
import com.uwc_cam_champion.backend.request.cam.CamRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CamService implements ICamService {

    private final CamRepository camRepository;
    private final UserRepository userRepository;

    public CamService(  CamRepository camRepository,UserRepository userRepository) { // constructor 

        this.camRepository = camRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cam addCam(Long userId, CamRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (camRepository.existsById(userId)) {
            throw new RuntimeException("CAM already exists for this user");
        }

        Cam cam = new Cam();

        cam.setUser(user);

        cam.setActualCam(  request.getActualCam() != null ? request.getActualCam() : new BigDecimal("0.00")
        );

        cam.setTargetCam(
                request.getTargetCam() != null
                        ? request.getTargetCam()
                        : new BigDecimal("50.00")
        );

        cam.setProjectedCam(
                request.getProjectedCam() != null
                        ? request.getProjectedCam()
                        : new BigDecimal("0.00")
        );

        return camRepository.save(cam);
    }

    @Override
    public Cam getCam(Long userId) {

        return camRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("CAM not found for user: " + userId));
    }

    @Override
    public Cam recalculateCam(Long userId) {

        Cam cam = camRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("CAM not found for user: " + userId));

        BigDecimal actualCam = cam.getActualCam();

        if (actualCam == null) {
            actualCam = new BigDecimal("0.00");
        }

        cam.setProjectedCam(actualCam);

        return camRepository.save(cam);
    }

    @Override
    public Cam updateCam(Long userId, CamRequest request) {

        Cam cam = camRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("CAM not found for user: " + userId));

        if (request.getActualCam() != null) {
            cam.setActualCam(request.getActualCam());
        }

        if (request.getTargetCam() != null) {
            cam.setTargetCam(request.getTargetCam());
        }

        if (request.getProjectedCam() != null) {
            cam.setProjectedCam(request.getProjectedCam());
        }

        return camRepository.save(cam);
    }

    @Override
    public void deleteCam(Long userId) {

        Cam cam = camRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("CAM not found for user: " + userId));

        camRepository.delete(cam);
    }
}