package com.taskManager.service.dto;

import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
//    public EmployeeDto(String username, String name, Long newDepartment) {
//        this.username = username;
//        this.jobTitle = name;
//        this.departmentId = newDepartment;
//    }
    private Long id;
    private String jobTitle;
    private String username;
    private String email;
    private Long departmentId;
}
