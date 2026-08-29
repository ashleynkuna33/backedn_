package com.uwc_cam_champion.backend.request.Dashboard;

import java.math.BigDecimal;
import java.util.List;

public class DashboardResponse {
    private BigDecimal actualCam;
    private int modulesAdded;

    private TaskStats  stats;

    private List<ModuleResponse> modules;
    private List<DeadlineResponse> deadlines;
    private List<ModuleResponse> moduleCards;

    public List<ModuleResponse> getModuleCards() {
        return moduleCards;
    }
    public void setModuleCards(List<ModuleResponse> moduleCards) {
        this.moduleCards = moduleCards;
    }
    public  DashboardResponse(){

    }

    public BigDecimal getActualCam() {
        return actualCam;
    }

    public void setActualCam(BigDecimal actualCam) {
        this.actualCam = actualCam;
    }

    public int getModulesAdded() {
        return modulesAdded;
    }

    public void setModulesAdded(int modulesAdded) {
        this.modulesAdded = modulesAdded;
    }

    public TaskStats getStats() {
        return stats;
    }

    public void setStats(TaskStats stats) {
        this.stats = stats;
    }

    public List<ModuleResponse> getModules() {
        return modules;
    }

    public void setModules(List<ModuleResponse> modules) {
        this.modules = modules;
    }

    public List<DeadlineResponse> getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(List<DeadlineResponse> deadlines) {
        this.deadlines = deadlines;
    }

}
