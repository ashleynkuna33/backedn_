package com.uwc_cam_champion.backend.services.Deadline;

import com.uwc_cam_champion.backend.models.Deadline;
import com.uwc_cam_champion.backend.request.deadline.*;

public interface IDeadlineService {

    Deadline addDeadline(DeadlineRequest request, Long userId);
    Deadline updateDeadline(DeadlineRequest request, Long id);
    void deleteDeadline(Long id);

}
