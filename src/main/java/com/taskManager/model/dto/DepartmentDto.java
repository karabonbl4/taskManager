package com.taskManager.model.dto;

import lombok.Data;

@Data
public class DepartmentDto {
    private Long id;
    private String name;
    private String location;
    private String manager;
    private String authUserFunction;
}
