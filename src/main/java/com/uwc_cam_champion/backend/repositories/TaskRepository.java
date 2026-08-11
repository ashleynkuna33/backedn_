package com.uwc_cam_champion.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uwc_cam_champion.backend.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByModuleInfoId(Long moduleId);
}
