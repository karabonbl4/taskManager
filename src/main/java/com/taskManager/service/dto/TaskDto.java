package com.taskManager.service.dto;

import com.taskManager.model.entity.Employee;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private StringBuilder description;
    private Timestamp deadLine;
    private Date workday;
    private List<Employee> executors;
}
