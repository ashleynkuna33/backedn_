package com.uwc_cam_champion.backend.services.Dashboard;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uwc_cam_champion.backend.models.Deadline;
import com.uwc_cam_champion.backend.models.UserModule;
import com.uwc_cam_champion.backend.models.UserTask;
import com.uwc_cam_champion.backend.repositories.CamRepository;
import com.uwc_cam_champion.backend.repositories.DeadlineRepository;
import com.uwc_cam_champion.backend.repositories.UserModuleRepository;
import com.uwc_cam_champion.backend.repositories.UserTaskRepository;
import com.uwc_cam_champion.backend.request.Dashboard.DashboardResponse;
import com.uwc_cam_champion.backend.request.Dashboard.DeadlineResponse;
import com.uwc_cam_champion.backend.request.Dashboard.ModuleResponse;
import com.uwc_cam_champion.backend.request.Dashboard.TaskStats;



@Service
public class DashboardService {
    private final CamRepository camRepository;
    private final UserModuleRepository userModuleRepository;
    private final DeadlineRepository deadlineRepository;
    private final UserTaskRepository userTaskRepository;

    public DashboardService(CamRepository camRepository, UserModuleRepository userModuleRepository, DeadlineRepository deadlineRepository, UserTaskRepository userTaskRepository) {
        this.camRepository = camRepository;
        this.userModuleRepository = userModuleRepository;
        this.deadlineRepository = deadlineRepository;
        this.userTaskRepository = userTaskRepository;
    }

    public DashboardResponse getDashboard(Long userId) {
        DashboardResponse response = new DashboardResponse();

        // CAM summary card
        camRepository.findById(userId).ifPresent(cam ->
            response.setActualCam(cam.getActualCam()));

        // Modules + per-module task stats
        List<UserModule> userModules = userModuleRepository.findByUserId(userId);
        List<ModuleResponse> modules = userModules.stream().map(um -> {
            List<UserTask> tasks = userTaskRepository.findByUserModule_User_Id(um.getId());
            TaskStats stats = computeTaskStats(tasks);

            ModuleResponse mr = new ModuleResponse();
            mr.setId(um.getId());
            mr.setName(um.getModuleInfo().getTitle());
            mr.setCode(um.getModuleInfo().getName());
            mr.setCredits(um.getModuleInfo().getCredits());
            mr.setExamDate(um.getModuleInfo().getExamDate());
            mr.setScore(um.getCurrentCam());
            mr.setTaskStats(stats);
            return mr;
        }).collect(Collectors.toList());
        response.setModules(modules);
        response.setModuleCards(modules);
        response.setModulesAdded(modules.size());

        // Deadlines (via teammate's repo)
        response.setDeadlines(deadlineRepository.findByUserIdOrderByDueDateAsc(userId).stream()
            .limit(4)
            .map(this::toDeadlineResponse)
            .collect(Collectors.toList()));

        return response;
    }
    private TaskStats computeTaskStats(List<UserTask> tasks) {
        long completed = tasks.stream()
                .filter(t -> Boolean.TRUE.equals(t.getIsCompleted()))
                .count();
        long inProgress = tasks.stream()
                .filter(t -> !Boolean.TRUE.equals(t.getIsCompleted()) && t.getMark() != null)
                .count();
        long notStarted = tasks.stream()
                .filter(t -> !Boolean.TRUE.equals(t.getIsCompleted()) && t.getMark() == null)
                .count();
        return new TaskStats(inProgress, completed, notStarted);
    }

    private DeadlineResponse toDeadlineResponse(Deadline deadline) {
        LocalDate dueLocalDate = deadline.getDueDate().toLocalDate();

        long daysUntilDue = ChronoUnit.DAYS.between(LocalDate.now(), dueLocalDate);
        String dueInfo;
        if (daysUntilDue < 0) {
            dueInfo = "Overdue";
        } else if (daysUntilDue == 0) {
            dueInfo = "Due Today";
        } else if (daysUntilDue == 1) {
            dueInfo = "Due Tomorrow";
        }
        else if (daysUntilDue < 30) {
            dueInfo = "Due in " + daysUntilDue + " days";
        }
        else {
            long months = Math.round(daysUntilDue / 30.0);
            dueInfo = "Due in " + months + (months ==1? " month": " months");
        }
        return new DeadlineResponse(
            dueLocalDate,
            deadline.getTitle(),
            dueInfo,
            deadline.getPriority()
        );
    }

}