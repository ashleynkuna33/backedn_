package com.uwc_cam_champion.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uwc_cam_champion.backend.models.Task;
import com.uwc_cam_champion.backend.request.moduleinfo.UserTaskSummaryResponse;
import com.uwc_cam_champion.backend.services.Module.UserModuleQueryService;
import com.uwc_cam_champion.backend.services.Task.TaskService;

@RestController
@RequestMapping("/api") // fixed: was missing entirely, so routes lived at /tasks/... instead of /api/tasks/...
public class TaskController {
   private final TaskService taskService;
   private final UserModuleQueryService userModuleQueryService;

    public TaskController(TaskService taskService, UserModuleQueryService userModuleQueryService) {
        this.taskService = taskService;
        this.userModuleQueryService = userModuleQueryService;
    }

    // Fixes the GET /api/tasks/user/{userId} 404 that was wiping every
    // login (see UserContext.jsx's loadUserData). Template-only, no marks —
    // matches this controller's existing pattern (see getTaskById below).
    @GetMapping("/tasks/user/{userId}")
    public List<UserTaskSummaryResponse> getAllTasksByUserId(@PathVariable Long userId) {
        return userModuleQueryService.getTasksForUser(userId);
    }

    @GetMapping("/tasks/module/{moduleId}")
    public List<Task> getAllTasksByModuleId(@PathVariable Long moduleId) {
        return taskService.getAllTasksByModuleId(moduleId);
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/tasks/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    } 
}
