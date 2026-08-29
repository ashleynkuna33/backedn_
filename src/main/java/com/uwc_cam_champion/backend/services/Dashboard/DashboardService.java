package com.uwc_cam_champion.backend.services.Dashboard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uwc_cam_champion.backend.models.Cam;
import com.uwc_cam_champion.backend.models.Deadline;
import com.uwc_cam_champion.backend.models.UserModule;
import com.uwc_cam_champion.backend.repositories.CamRepository;
import com.uwc_cam_champion.backend.repositories.DeadlineRepository;
import com.uwc_cam_champion.backend.repositories.UserModuleRepository;
import com.uwc_cam_champion.backend.request.Dashboard.DashboardResponse;
import com.uwc_cam_champion.backend.request.Dashboard.DeadlineResponse;
import com.uwc_cam_champion.backend.request.Dashboard.ModuleResponse;



@Service
public class DashboardService {
    private final CamRepository camRepository;
    private final UserModuleRepository userModuleRepository;
    private final DeadlineRepository deadlineRepository;

    public DashboardService(CamRepository camRepository, UserModuleRepository userModuleRepository, DeadlineRepository deadlineRepository) {
        this.camRepository = camRepository;
        this.userModuleRepository = userModuleRepository;
        this.deadlineRepository = deadlineRepository;
    }

    public DashboardResponse getDashboard(Long userId) {
        Cam cam = camRepository.findById(userId).orElse(null);
        DashboardResponse response = new DashboardResponse();

        response.setActualCam(
            cam != null ? cam.getActualCam() : java.math.BigDecimal.ZERO
        );

        List<UserModule> userModules = userModuleRepository.findByUserId(userId);

        response.setModulesAdded(userModules.size());

        List<ModuleResponse> moduleCards = userModules.stream().map(this::toModuleResponse).collect(Collectors.toList());

        response.setModuleCards(moduleCards);

        List<Deadline> deadlines = deadlineRepository.findByUserIdOrderByDueDateAsc(userId);
        List<DeadlineResponse> deadlineResponses = deadlines.stream().limit(4).map(this::toDeadlineResponse).collect(Collectors.toList());

        response.setDeadlines(deadlineResponses);

        return response;
    
    }
    private ModuleResponse toModuleResponse(UserModule userModule) {
        BigDecimal camValue = userModule.getCurrentCam();
        int progress = camValue.setScale(0, RoundingMode.HALF_UP).intValue();

        String status;
        String statusColor;

        if (camValue.compareTo(new BigDecimal("75")) >= 0) {
            status = "Excellent";
            statusColor = "#0ea5e9"; // Blue
        } else if (camValue.compareTo(new BigDecimal("50")) >= 0) {
            status = "On Track";
            statusColor = "#10b981"; // Green
        } else {
            status = "At Risk";
            statusColor = "#f97316"; // Red
        }


        return new ModuleResponse(
            userModule.getId(),
            userModule.getModuleInfo().getTitle(), //display name
            userModule.getModuleInfo().getName(), // display code
            camValue,
            progress,
            status,
            statusColor
        );
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