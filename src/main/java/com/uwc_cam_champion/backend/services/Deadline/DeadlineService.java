package com.uwc_cam_champion.backend.services.Deadline;

import com.uwc_cam_champion.backend.exceptions.ResourceNotFoundException;
import com.uwc_cam_champion.backend.models.Deadline;
import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.repositories.DeadlineRepository;
import com.uwc_cam_champion.backend.repositories.UserRepository;
import com.uwc_cam_champion.backend.request.deadline.DeadlineRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeadlineService implements IDeadlineService {

    private final DeadlineRepository deadlineRepository;
    private final UserRepository userRepository;

    public DeadlineService(DeadlineRepository deadlineRepository, UserRepository userRepository) {
        this.deadlineRepository = deadlineRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Deadline addDeadline(DeadlineRequest request, Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Deadline deadline = new Deadline();
        applyDeadlineRequest(deadline, request);
        deadline.setUser(user);

        return deadlineRepository.save(deadline);
    }

    @Override
    @Transactional
    public Deadline updateDeadline(DeadlineRequest request, Long id) {
        Deadline deadline = deadlineRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deadline not found with id: " + id));

        applyDeadlineRequest(deadline, request);

        return deadlineRepository.save(deadline);
    }

    @Override
    @Transactional
    public void deleteDeadline(Long id) {
        Deadline deadline = deadlineRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deadline not found with id: " + id));
        deadlineRepository.delete(deadline);
    }

    private void applyDeadlineRequest(Deadline deadline, DeadlineRequest request) {
        deadline.setTitle(request.getTitle());
        deadline.setDueDate(request.getDueDate());
        deadline.setInfo(request.getInfo());
        deadline.setPriority(request.getPriority() != null ? request.getPriority() : "Medium");
        deadline.setCompleted(request.isCompleted());
    }
}


