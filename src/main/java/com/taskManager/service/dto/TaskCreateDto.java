package com.taskManager.service.dto;

import lombok.Data;

import java.util.Date;


@Data
public class TaskCreateDto {
    private Long id;
    private String name;
    private String description;
    private Date deadlineDate;
    private String deadlineTime;
    private Integer priority;
    private Date workday;
    private String executors;
    private Long departmentId;
}
