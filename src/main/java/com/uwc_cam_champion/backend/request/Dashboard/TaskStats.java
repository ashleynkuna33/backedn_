package com.uwc_cam_champion.backend.request.Dashboard;

public class TaskStats {
    private long pendingTasks;
    private long completedTasks;
    private long totalTasks;

    public TaskStats() {}

    public TaskStats(long inProgress, long completed, long notStarted) {
        this.pendingTasks = inProgress;
        this.completedTasks = completed;
        this.totalTasks = notStarted;
    }

    public long getInProgress() { return pendingTasks; }
    public void setInProgress(long inProgress) { this.pendingTasks = inProgress; }

    public long getCompleted() { return completedTasks; }
    public void setCompleted(long completed) { this.completedTasks = completed; }

    public long getNotStarted() { return totalTasks; }
    public void setNotStarted(long notStarted) { this.totalTasks = notStarted; }
}
