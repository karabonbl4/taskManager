package com.taskManager.service.impl;


import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.*;
import com.taskManager.service.converter.*;
import com.taskManager.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserService userService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final DateConverter dateConverter;
    private final CustomerConverter customerConverter;
    private final MaterialConverter materialConverter;
    private final EmployeeConverter employeeConverter;
    private final ProviderConverter providerConverter;
    private final DepartmentConverter departmentConverter;

    @Override
    public boolean save(DepartmentDto departmentDto) {
        if (findByAuthUsername().stream()
                .anyMatch(dept -> dept.getName().equalsIgnoreCase(departmentDto.getName())) || departmentDto.getName().equalsIgnoreCase("")) {
            return false;
        }
        employeeService.save(new Employee(userService.getAuthUser(), "manager", departmentRepository.save(departmentConverter.convertToDepartment(departmentDto))));
        return true;
    }

    @Override
    public List<Department> findByAuthUsername() {
        return departmentRepository.findByEmployees_User_Username(userService.getAuthUser().getUsername());
    }

    @Override
    public List<DepartmentDto> getDepartmentsDto() {
        return findByAuthUsername().stream()
                .map(departmentConverter::convertToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getDepartmentEmployees(Long departmentId) {
        return departmentRepository.getReferenceById(departmentId).getEmployees().stream()
                .filter(employee -> !employee.isDeleted())
                .map(employeeConverter::convertToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDto> getDepartmentMaterials(Long departmentId) {
        return departmentRepository.getReferenceById(departmentId).getMaterials().stream()
                .filter(material -> !material.isDeleted())
                .map(materialConverter::convertToMaterialDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderDto> getDepartmentProviders(Long departmentId) {
        return companyService.findProviderByDepartmentId(departmentId);
    }

    @Override
    public List<CustomerDto> getDepartmentCustomers(Long departmentId) {
        return companyService.findCustomerByDepartmentId(departmentId);
    }

    @Override
    public boolean update(DepartmentDto departmentDto) {
        var dbDepartment = departmentRepository.getReferenceById(departmentDto.getId());
        if (dbDepartment.getName().equals(departmentDto.getName()) & dbDepartment.getLocation().equals(departmentDto.getLocation())){
            return false;
        }
        dbDepartment.setName(departmentDto.getName());
        dbDepartment.setLocation(departmentDto.getLocation());
        departmentRepository.saveAndFlush(dbDepartment);
        return true;
    }

    @Override
    public WorkDayWithDepartmentIdDto getWorkdayToday() {
        var workdayToday = new WorkDayWithDepartmentIdDto();
        workdayToday.setDate(dateConverter.convertLocalToDate(LocalDate.now()));
        return workdayToday;
    }

    @Override
    public boolean containEmployee(EmployeeDto employeeDto) {
        var department = departmentRepository.getReferenceById(employeeDto.getDepartmentId());
        return department.getEmployees().stream()
                .anyMatch(employee -> employee.getUser().getEmail().equals(employeeDto.getEmail()));
    }


    @Override
    public DepartmentDto findById(long id) {
        return departmentConverter.convertToDepartmentDto(departmentRepository.getReferenceById(id));
    }

}
