package com.uwc_cam_champion.backend.request.Dashboard;

public class TaskStats {
    private long inProgress;
    private long completed;
    private long notStarted;

    public TaskStats() {}

    public TaskStats(long inProgress, long completed, long notStarted) {
        this.inProgress = inProgress;
        this.completed = completed;
        this.notStarted = notStarted;
    }

    public long getInProgress() { return inProgress; }
    public void setInProgress(long inProgress) { this.inProgress = inProgress; }

    public long getCompleted() { return completed; }
    public void setCompleted(long completed) { this.completed = completed; }

    public long getNotStarted() { return notStarted; }
    public void setNotStarted(long notStarted) { this.notStarted = notStarted; }
}
