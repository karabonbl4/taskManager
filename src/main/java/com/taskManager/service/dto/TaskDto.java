package com.taskManager.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String condition;
    private Integer priority;
    private Long departmentId;
    private String executors;
    private String tempMaterials;
}
