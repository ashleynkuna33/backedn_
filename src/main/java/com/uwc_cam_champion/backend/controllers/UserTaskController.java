package com.uwc_cam_champion.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uwc_cam_champion.backend.request.UserTask.UpdateUserTaskRequest;
import com.uwc_cam_champion.backend.request.UserTask.UserTaskResponse;
import com.uwc_cam_champion.backend.services.UserTask.UserTaskService;

@RestController
@RequestMapping("/api/user-tasks")
// NOTE: no trailing slash here, unlike taskController's existing @CrossOrigin —
// see item #4 on the punch list, that trailing slash likely breaks CORS matching.
@CrossOrigin(origins = "http://localhost:5173")
public class UserTaskController {

    private final UserTaskService userTaskService;

    public UserTaskController(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    // GET /api/user-tasks/module/{moduleId}/user/{userId}
    // Returns this student's tasks for one module, each combining the
    // shared Task template (name, weight, due date) with their personal
    // mark and completion state from UserTask.
    @GetMapping("/module/{moduleId}/user/{userId}")
    public List<UserTaskResponse> getUserTasksForModule(
            @PathVariable Long moduleId,
            @PathVariable Long userId) {
        return userTaskService.getUserTasksForModule(userId, moduleId);
    }

    // PATCH /api/user-tasks/{userTaskId}
    // Saves a student's own mark/completion for one task. Deliberately
    // separate from PUT /tasks/{taskId} — this only ever touches the
    // UserTask row (one student, one task), never the shared Task
    // template that every student in the module reads from.
    @PatchMapping("/{userTaskId}")
    public UserTaskResponse updateUserTaskMark(
            @PathVariable Long userTaskId,
            @RequestBody UpdateUserTaskRequest request) {
        return userTaskService.updateUserTaskMark(
            userTaskId,
            request.getMark(),
            request.getIsCompleted()
        );
    }
}