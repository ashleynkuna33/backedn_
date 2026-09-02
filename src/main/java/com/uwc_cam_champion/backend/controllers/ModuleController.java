package com.uwc_cam_champion.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uwc_cam_champion.backend.request.moduleinfo.UserModuleSummaryResponse;
import com.uwc_cam_champion.backend.services.Module.UserModuleQueryService;

// Did not exist before — confirmed against the controllers folder, which
// only had Cam/Dashboard/Deadline/task/User. This is the fix for the
// GET /api/modules/user/{userId} 404 that was wiping every login.
@RestController
@RequestMapping("/api/modules")
@CrossOrigin(origins = "http://localhost:5173")
public class ModuleController {

    private final UserModuleQueryService userModuleQueryService;

    public ModuleController(UserModuleQueryService userModuleQueryService) {
        this.userModuleQueryService = userModuleQueryService;
    }

    @GetMapping("/user/{userId}")
    public List<UserModuleSummaryResponse> getModulesForUser(@PathVariable Long userId) {
        return userModuleQueryService.getModulesForUser(userId);
    }
}