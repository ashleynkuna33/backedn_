package com.uwc_cam_champion.backend.request.moduleinfo;

import java.math.BigDecimal;

// Fixes the missing GET /api/modules/user/{userId} route (UserContext.jsx
// calls this on every login). Deliberately reads UserModule.currentCam
// directly rather than recomputing a "score" — that field is already
// stored and correct, no need to guess at DashboardService's logic.
public class UserModuleSummaryResponse {

    private Long userModuleId;
    private Long moduleId;
    private String name;
    private String title;
    private Integer credits;
    private BigDecimal currentCam;
    private Boolean isCompleted;

    public UserModuleSummaryResponse() {}

    public Long getUserModuleId() { return userModuleId; }
    public void setUserModuleId(Long userModuleId) { this.userModuleId = userModuleId; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public BigDecimal getCurrentCam() { return currentCam; }
    public void setCurrentCam(BigDecimal currentCam) { this.currentCam = currentCam; }

    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}