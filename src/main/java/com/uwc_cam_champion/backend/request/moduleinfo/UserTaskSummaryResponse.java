package com.uwc_cam_champion.backend.request.moduleinfo;

import java.math.BigDecimal;
import java.time.LocalDate;

// Fixes the missing GET /api/tasks/user/{userId} route. Template-only,
// matching taskController's existing pattern elsewhere (no marks here —
// marks live on UserTask, see UserTaskController for that).
public class UserTaskSummaryResponse {

    private Long taskId;
    private Long moduleId;
    private String moduleName;
    private String type;
    private String name;
    private String subName;
    private LocalDate dueDate;
    private BigDecimal taskWeight;
    private BigDecimal categoryWeight;

    public UserTaskSummaryResponse() {}

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public String getModuleName() { return moduleName; }
    public void setModuleName(String moduleName) { this.moduleName = moduleName; }

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
}