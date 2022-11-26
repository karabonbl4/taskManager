package com.taskManager.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskManager.model.entity.Employee;
import lombok.Data;
import org.intellij.lang.annotations.Pattern;

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
    private Integer priority;
    private Long departmentId;
    private String executors;
}
