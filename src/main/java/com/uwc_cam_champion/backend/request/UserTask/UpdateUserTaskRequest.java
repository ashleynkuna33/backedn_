package com.uwc_cam_champion.backend.request.UserTask;

import java.math.BigDecimal;

// Body for PATCH /api/user-tasks/{userTaskId}.
// Both fields optional — send just `mark`, just `isCompleted`, or both.
public class UpdateUserTaskRequest {

    private BigDecimal mark;
    private Boolean isCompleted;

    public UpdateUserTaskRequest() {}

    public BigDecimal getMark() { return mark; }
    public void setMark(BigDecimal mark) { this.mark = mark; }

    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}