package com.taskManager.service.dto;

import lombok.Data;

import java.util.Date;


@Data
public class WorkDayWithDepartmentIdDto {
    private Date date;
    private long departmentId;
}
