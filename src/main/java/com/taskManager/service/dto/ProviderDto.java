package com.taskManager.service.dto;

import lombok.Data;

@Data
public class ProviderDto {
    private Long id;
    private String name;
    private Integer taxNumber;
    private String location;
    private String email;
    private Long departmentId;
}