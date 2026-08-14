package com.uwc_cam_champion.backend.request.moduleinfo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)
public class DeleteTaskRequest {

    private Long taskId;
    
}
