package com.uwc_cam_champion.backend.request.deadline;

import java.time.LocalDateTime;

public class DeadlineRequest {

    private String title;
    private LocalDateTime dueDate;
    private String info;
    private String priority;
    private boolean isCompleted;

    public String getTitle() { return title; }
    public LocalDateTime getDueDate() { return dueDate; }
    public String getInfo() { return info; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }

    public void setTitle(String title) { this.title = title; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public void setInfo(String info) { this.info = info; }
    public void setPriority(String priority) { this.priority = priority; }
    public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

}
