package com.uwc_cam_champion.backend.request.UserTask;

import java.math.BigDecimal;
import java.time.LocalDate;

// Flattened view of a UserTask + its Task template, so the frontend gets
// name/weight/dueDate (from Task) and mark/isCompleted (from UserTask) in
// one object per row instead of a nested structure.
public class UserTaskResponse {

    private Long userTaskId; // UserTask.id — use this when saving a mark
    private Long taskId;     // Task.id — the shared template this is based on

    private String type;
    private String name;
    private String subName;
    private LocalDate dueDate;
    private BigDecimal taskWeight;
    private BigDecimal categoryWeight;
    private String description;

    private BigDecimal mark;
    private Boolean isCompleted;

    public UserTaskResponse() {}

    public Long getUserTaskId() { return userTaskId; }
    public void setUserTaskId(Long userTaskId) { this.userTaskId = userTaskId; }

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSubName() { return subName; }
    public void setSubName(String subName) { this.subName = subName; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public BigDecimal getTaskWeight() { return taskWeight; }
    public void setTaskWeight(BigDecimal taskWeight) { this.taskWeight = taskWeight; }

    public BigDecimal getCategoryWeight() { return categoryWeight; }
    public void setCategoryWeight(BigDecimal categoryWeight) { this.categoryWeight = categoryWeight; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getMark() { return mark; }
    public void setMark(BigDecimal mark) { this.mark = mark; }

    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}