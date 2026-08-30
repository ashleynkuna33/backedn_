package com.uwc_cam_champion.backend.services.UserTask;

import java.util.List;

import com.uwc_cam_champion.backend.request.UserTask.UserTaskResponse;

public interface IUserTask {

    List<UserTaskResponse> getUserTasksForModule(Long userId, Long moduleId);

    // Added now so the interface doesn't need a second edit when we build
    // the "save a mark" endpoint next (punch-list item #2).
    UserTaskResponse updateUserTaskMark(Long userTaskId, java.math.BigDecimal mark, Boolean isCompleted);
}