package com.uwc_cam_champion.backend.services.Task;

import com.uwc_cam_champion.backend.models.Task;
import java.util.List;

public interface ITaskService {

    List<Task> getAllTasksByModuleId(Long moduleId);
    Task getTaskById(Long taskId);
    Task createTask(Task task);
    Task updateTask(Long taskId, Task task);
    void deleteTask(Long taskId);

}
