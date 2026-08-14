
package com.uwc_cam_champion.backend.request.moduleinfo;
import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)
public class AddModuleRequest {

    private String name;
    private  String description;
    private String title;
    private Integer credits;
    private LocalDate exam_date;



    
}