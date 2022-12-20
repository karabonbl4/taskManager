package com.taskManager.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskManager.model.enums.State;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadlineDate;
    private String deadlineTime;
    private Date workday;
    private State condition;
    private Integer priority;
    private Long departmentId;
    private String executors;
    private String tempMaterials;
}
