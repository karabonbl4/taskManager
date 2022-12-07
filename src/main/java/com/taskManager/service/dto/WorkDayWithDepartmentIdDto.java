package com.taskManager.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkDayWithDepartmentIdDto {
    private Date date;
    private long departmentId;
    private Integer page;
}
