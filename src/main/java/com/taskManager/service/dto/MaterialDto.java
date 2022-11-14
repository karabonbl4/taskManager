package com.taskManager.service.dto;

import lombok.Data;

@Data
public class MaterialDto {
    private Long id;
    private String name;
    private String property;
    private Integer value;
    private Long departmentId;
}
