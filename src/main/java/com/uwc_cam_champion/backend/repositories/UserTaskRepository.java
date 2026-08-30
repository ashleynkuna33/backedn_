package com.uwc_cam_champion.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uwc_cam_champion.backend.models.UserTask;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findByUserModule_ModuleInfo_Id(Long moduleId);
    List<UserTask> findByTask_Id(Long taskId);
    List<UserTask> findByUserModule_User_Id(Long userId);
    List<UserTask> findByUserModule_ModuleInfo_IdAndUserModule_User_Id(Long moduleId, Long userId);

}
