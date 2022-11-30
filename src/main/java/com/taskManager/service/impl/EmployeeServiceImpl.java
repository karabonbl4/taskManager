package com.taskManager.service.impl;

import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.dto.EmployeeDto;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.repository.EmployeeRepository;
import com.taskManager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public void save(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void saveAll(List<Employee> employees) {
        employeeRepository.saveAllAndFlush(employees);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.getReferenceById(id);
    }

    @Override
    public boolean update(EmployeeDto editEmployee) {
        var existEmployees = departmentRepository.getReferenceById(editEmployee.getDepartmentId()).getEmployees();
        if (existEmployees.stream()
                .anyMatch(employee -> employee.getName().equals(editEmployee.getJobTitle()))) {
            return false;
        }
        var dbEmployee = employeeRepository.getReferenceById(editEmployee.getId());
        dbEmployee.setName(editEmployee.getJobTitle());
        employeeRepository.saveAndFlush(dbEmployee);
        return true;
    }

    public void delete(EmployeeDto employeeDto) {
        var dbEmployee = employeeRepository.getReferenceById(employeeDto.getId());
        dbEmployee.setName("deleted");
        employeeRepository.saveAndFlush(dbEmployee);
    }

}
