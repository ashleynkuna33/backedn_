package com.uwc_cam_champion.backend.services.Module;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwc_cam_champion.backend.models.ModuleInfo;
import com.uwc_cam_champion.backend.models.Task;
import com.uwc_cam_champion.backend.models.UserModule;
import com.uwc_cam_champion.backend.repositories.UserModuleRepository;
import com.uwc_cam_champion.backend.request.moduleinfo.UserModuleSummaryResponse;
import com.uwc_cam_champion.backend.request.moduleinfo.UserTaskSummaryResponse;

@Service
public class UserModuleQueryService {

    private final UserModuleRepository userModuleRepository;

    public UserModuleQueryService(UserModuleRepository userModuleRepository) {
        this.userModuleRepository = userModuleRepository;
    }

    // @Transactional matters here: with spring.jpa.open-in-view=false,
    // UserModule.moduleInfo (LAZY) would otherwise become inaccessible the
    // moment this method returns. Keeping the session open for the whole
    // method — and fully mapping to DTOs before returning — means nothing
    // lazy ever escapes to Jackson.
    @Transactional(readOnly = true)
    public List<UserModuleSummaryResponse> getModulesForUser(Long userId) {
        List<UserModule> userModules = userModuleRepository.findByUserId(userId);
        return userModules.stream().map(this::toModuleSummary).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserTaskSummaryResponse> getTasksForUser(Long userId) {
        List<UserModule> userModules = userModuleRepository.findByUserId(userId);
        return userModules.stream()
            .flatMap(um -> {
                ModuleInfo module = um.getModuleInfo();
                return module.getTasks().stream().map(task -> toTaskSummary(task, module));
            })
            .collect(Collectors.toList());
    }

    private UserModuleSummaryResponse toModuleSummary(UserModule userModule) {
        ModuleInfo module = userModule.getModuleInfo();

        UserModuleSummaryResponse dto = new UserModuleSummaryResponse();
        dto.setUserModuleId(userModule.getId());
        dto.setModuleId(module.getId());
        dto.setName(module.getName());
        dto.setTitle(module.getTitle());
        dto.setCredits(module.getCredits());
        dto.setCurrentCam(userModule.getCurrentCam());
        dto.setIsCompleted(userModule.getIsCompleted());
        return dto;
    }

    private UserTaskSummaryResponse toTaskSummary(Task task, ModuleInfo module) {
        UserTaskSummaryResponse dto = new UserTaskSummaryResponse();
        dto.setTaskId(task.getId());
        dto.setModuleId(module.getId());
        dto.setModuleName(module.getName());
        dto.setType(task.getType());
        dto.setName(task.getName());
        dto.setSubName(task.getSubName());
        dto.setDueDate(task.getDueDate());
        dto.setTaskWeight(task.getTaskWeight());
        dto.setCategoryWeight(task.getCategoryWeight());
        return dto;
    }
}