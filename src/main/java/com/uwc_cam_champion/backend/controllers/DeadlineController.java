package com.uwc_cam_champion.backend.controllers;

import com.uwc_cam_champion.backend.models.Deadline;
import com.uwc_cam_champion.backend.request.deadline.DeadlineRequest;
import com.uwc_cam_champion.backend.services.Deadline.IDeadlineService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deadlines")
public class DeadlineController {

    private final IDeadlineService deadlineService;

    public DeadlineController(IDeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Deadline addDeadline(@PathVariable Long userId, @RequestBody DeadlineRequest request) {
        return deadlineService.addDeadline(request, userId);
    }

    @PutMapping("/{deadlineId}")
    public Deadline updateDeadline(@PathVariable Long deadlineId, @RequestBody DeadlineRequest request) {
        return deadlineService.updateDeadline(request, deadlineId);
    }

    @DeleteMapping("/{deadlineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeadline(@PathVariable Long deadlineId) {
        deadlineService.deleteDeadline(deadlineId);
    }
}
