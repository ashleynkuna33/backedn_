package com.uwc_cam_champion.backend.request.Dashboard;

import java.time.LocalDate;

public class DeadlineResponse {
    private LocalDate dueDate;
    private String title;
    private String dueInfo;
    private String priority;

    public DeadlineResponse(LocalDate dueDate, String title, String dueInfo, String priority) {
        this.dueDate = dueDate;
        this.title = title;
        this.dueInfo = dueInfo;
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueInfo() {
        return dueInfo;
    }

    public void setDueInfo(String dueInfo) {
        this.dueInfo = dueInfo;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
