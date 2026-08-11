package com.uwc_cam_champion.backend.services.Module;

import com.uwc_cam_champion.backend.models.ModuleInfo;
import com.uwc_cam_champion.backend.models.Task;
import com.uwc_cam_champion.backend.request.moduleinfo.*;

public interface IModuleInfoService {

    ModuleInfo addModule(Long creatorId, AddModuleRequest request);
    ModuleInfo updateModule(Long creatorId, UpdateModuleRequest request);
    void deleteModule(Long moduleId, DeleteModuleRequest request);
    

    Task addTask(Long moduleId, AddTaskRequest request);
    Task updateTask(Long taskId,UpdateTaskRequest request);
    void deleteTask(Long moduleId, Long taskId, DeleteTaskRequest request);
    

}
