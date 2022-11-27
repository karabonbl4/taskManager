package com.taskManager.service.impl;

import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.model.repository.UserRepository;
import com.taskManager.service.dto.EmployeeDto;
import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.entity.User;
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
    private final UserRepository userRepository;

    @Override
    public Employee findByUserAndDepartment(User user, Department department) {
        return employeeRepository.findByUserAndDepartment(user, department);
    }

    @Override
    public List<Employee> findByUser(User user) {
        return employeeRepository.findByUser(user);
    }

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
    public EmployeeDto convertToEmployeeDto(Employee employee) {
        var employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setJobTitle(employee.getName());
        employeeDto.setUsername(employee.getUser().getUsername());
        employeeDto.setEmail(employee.getUser().getEmail());
        employeeDto.setDepartmentId(employee.getDepartment().getId());
        return employeeDto;
    }


    @Override
    public Employee convertToEmployee(EmployeeDto employeeDto) {
        var user = userRepository.findByUsername(employeeDto.getUsername());
        var employee = new Employee();
        if (employeeDto.getId()!=null){
            employee.setId(employeeDto.getId());
        }
        employee.setName(employeeDto.getJobTitle());
        employee.setUser(user);
        employee.setDepartment(departmentRepository.getReferenceById(employeeDto.getDepartmentId()));
        return employee;
    }

    @Override
    public boolean update(EmployeeDto editEmployee) {
        var existEmployees = departmentRepository.getReferenceById(editEmployee.getDepartmentId());
        if(existEmployees.getEmployees().stream()
                .anyMatch(employee -> employee.getName().equals(editEmployee.getJobTitle()))){
            return false;
        }
        var dbEmployee = employeeRepository.getReferenceById(editEmployee.getId());
        dbEmployee.setName(editEmployee.getJobTitle());
        employeeRepository.saveAndFlush(dbEmployee);
        return true;
    }
    public void delete(EmployeeDto employeeDto){
        var dbEmployee = employeeRepository.getReferenceById(employeeDto.getId());
        dbEmployee.setName("deleted");
        employeeRepository.saveAndFlush(dbEmployee);
    }

}
