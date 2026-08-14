package com.uwc_cam_champion.backend.request.moduleinfo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(onlyExplicitlyIncluded = true)
public class AddTaskRequest {

    private String name;
    private String sub_name;
    private BigDecimal task_weight;
    private BigDecimal category_weight;
    private LocalDate due_date;
    private String type;
    private String description;
   

   
}
