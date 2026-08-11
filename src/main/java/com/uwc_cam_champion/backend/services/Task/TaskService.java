package com.uwc_cam_champion.backend.services.Task;

import java.util.List;

import com.uwc_cam_champion.backend.models.Task;
import com.uwc_cam_champion.backend.repositories.TaskRepository;

public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    @Override
    public List<Task> getAllTasksByModuleId(Long moduleId) {
        return taskRepository.findByModuleInfoId(moduleId);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        existingTask.setModuleInfo(task.getModuleInfo());
        existingTask.setType(task.getType());
        existingTask.setName(task.getName());
        existingTask.setSubName(task.getSubName());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setTaskWeight(task.getTaskWeight());
        existingTask.setCategoryWeight(task.getCategoryWeight());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        taskRepository.delete(existingTask);
    }
    
}
