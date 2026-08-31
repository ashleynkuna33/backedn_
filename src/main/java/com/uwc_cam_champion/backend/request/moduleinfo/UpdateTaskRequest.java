package com.uwc_cam_champion.backend.request.moduleinfo;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)
public class UpdateTaskRequest {

    private String name;
    private String sub_name;
    private BigDecimal task_weight;
    private BigDecimal category_weight;
    private LocalDate due_date;
    private String type;
    private String description;

    public UpdateTaskRequest() {}

    public UpdateTaskRequest(String name, String sub_name, BigDecimal task_weight, BigDecimal category_weight, LocalDate due_date, String type, String description) {
        this.name = name;
        this.sub_name = sub_name;
        this.task_weight = task_weight;
        this.category_weight = category_weight;
        this.due_date = due_date;
        this.type = type;
        this.description = description;
    }

    

    

}
