package com.uwc_cam_champion.backend.request.Dashboard;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ModuleResponse {
    private Long id;
    private String name;
    private String code;
    private BigDecimal score;
    private int progress;
    private String status;
    private String statusColor;
    private int credits;
    private LocalDate examDate;
    private TaskStats taskStats;

    public ModuleResponse(){

    }

    public ModuleResponse(Long id, String name, String code, BigDecimal score, int progress, String status, String statusColor) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.score = score;
        this.progress = progress;
        this.status = status;
        this.statusColor = statusColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public TaskStats getTaskStats() {
        return taskStats;
    }

    public void setTaskStats(TaskStats taskStats) {
        this.taskStats = taskStats;
    }
}
