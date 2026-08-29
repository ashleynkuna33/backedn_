package com.uwc_cam_champion.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uwc_cam_champion.backend.models.Deadline;

public interface DeadlineRepository extends JpaRepository<Deadline, Long> {
    List<Deadline> findByUserIdOrderByDueDateAsc(Long userId);
}
