package com.taskManager.service.dto;

import com.taskManager.model.entity.Employee;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private Date deadLine;
    private Integer priority;
    private Date workday;
    private List<Employee> employees;
}
