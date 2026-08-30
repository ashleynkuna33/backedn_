package com.uwc_cam_champion.backend.services.UserTask;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uwc_cam_champion.backend.models.Task;
import com.uwc_cam_champion.backend.models.UserTask;
import com.uwc_cam_champion.backend.repositories.UserTaskRepository;
import com.uwc_cam_champion.backend.request.UserTask.UserTaskResponse;

@Service
public class UserTaskService implements IUserTask {

    private final UserTaskRepository userTaskRepository;

    public UserTaskService(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    @Override
    public List<UserTaskResponse> getUserTasksForModule(Long userId, Long moduleId) {
        List<UserTask> userTasks =
            userTaskRepository.findByUserModule_ModuleInfo_IdAndUserModule_User_Id(moduleId, userId);

        return userTasks.stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
    }

    // Updates a student's own mark/completion for one task. Deliberately
    // operates on UserTask only — never the shared Task template — so
    // saving a mark can't accidentally change the assignment for every
    // other student in the module.
    @Override
    public UserTaskResponse updateUserTaskMark(Long userTaskId, BigDecimal mark, Boolean isCompleted) {
        UserTask userTask = userTaskRepository.findById(userTaskId)
            .orElseThrow(() -> new RuntimeException("UserTask not found: " + userTaskId));

        if (mark != null) {
            userTask.setMark(mark);
        }
        if (isCompleted != null) {
            userTask.setIsCompleted(isCompleted);
        }

        UserTask saved = userTaskRepository.save(userTask);
        return toResponse(saved);
    }

    private UserTaskResponse toResponse(UserTask userTask) {
        Task task = userTask.getTask();

        UserTaskResponse dto = new UserTaskResponse();
        dto.setUserTaskId(userTask.getId());
        dto.setTaskId(task.getId());
        dto.setType(task.getType());
        dto.setName(task.getName());
        dto.setSubName(task.getSubName());
        dto.setDueDate(task.getDueDate());
        dto.setTaskWeight(task.getTaskWeight());
        dto.setCategoryWeight(task.getCategoryWeight());
        dto.setDescription(task.getDescription());
        dto.setMark(userTask.getMark());
        dto.setIsCompleted(userTask.getIsCompleted());
        return dto;
    }
}