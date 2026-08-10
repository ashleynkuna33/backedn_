package com.uwc_cam_champion.backend.controllers;

import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.request.cam.CamRequest;
import com.uwc_cam_champion.backend.services.Cam.ICamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cam")
@CrossOrigin(origins = "http://localhost:5173")
public class CamController {

    private final ICamService camService;

    public CamController(ICamService camService) {
        this.camService = camService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Cam> addCam(
            @PathVariable Long userId,
            @RequestBody CamRequest request) {

        Cam cam = camService.addCam(userId, request);

        return new ResponseEntity<>(cam, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cam> getCam(
            @PathVariable Long userId) {

        Cam cam = camService.getCam(userId);

        return ResponseEntity.ok(cam);
    }

    @PostMapping("/{userId}/recalculate")
    public ResponseEntity<Cam> recalculateCam(
            @PathVariable Long userId) {

        Cam cam = camService.recalculateCam(userId);

        return ResponseEntity.ok(cam);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Cam> updateCam(
            @PathVariable Long userId,
            @RequestBody CamRequest request) {

        Cam cam = camService.updateCam(userId, request);

        return ResponseEntity.ok(cam);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCam(
            @PathVariable Long userId) {

        camService.deleteCam(userId);

        return ResponseEntity.noContent().build();
    }
}