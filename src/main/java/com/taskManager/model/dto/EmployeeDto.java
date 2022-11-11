package com.taskManager.model.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String jobTitle;
    private String username;
    private String email;
    private Long departmentId;
}
