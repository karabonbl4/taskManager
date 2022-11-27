package com.taskManager.service.impl;


import com.taskManager.model.entity.Department;
import com.taskManager.model.entity.Employee;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.*;
import com.taskManager.service.converter.DateConverter;
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
    private final CustomerService customerService;
    private final MaterialService materialService;
    private final ProviderService providerService;
    private final DateConverter dateConverter;

    @Override
    public boolean save(DepartmentDto departmentDto) {
        if (findByAuthUsername().stream()
                .anyMatch(dept -> dept.getName().equalsIgnoreCase(departmentDto.getName())) || departmentDto.getName().equalsIgnoreCase("")) {
            return false;
        }
        employeeService.save(new Employee(userService.getAuthUser(), "manager", departmentRepository.save(convertToDepartment(departmentDto))));
        return true;
    }

    @Override
    public List<Department> findByAuthUsername() {
        return departmentRepository.findByEmployees_User_Username(userService.getAuthUser().getUsername());
    }

    @Override
    public List<DepartmentDto> getDepartmentsDto() {
        return findByAuthUsername().stream()
                .map(this::convertToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto convertToDepartmentDto(Department department) {
        var departmentDto = new DepartmentDto();
        var employees = department.getEmployees();
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase("manager")) {
                departmentDto.setManager(employee.getUser().getUsername());
            }
            if (employee.getUser().getUsername().equals(userService.getAuthUser().getUsername())) {
                departmentDto.setAuthUserFunction(employee.getName());
            }
        }
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setLocation(department.getLocation());
        return departmentDto;
    }

    @Override
    public Department convertToDepartment(DepartmentDto departmentDto) {
        var department = new Department();
        if(departmentDto.getId()!=null){
            department.setId(departmentDto.getId());
        }
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }

    @Override
    public List<EmployeeDto> getDepartmentEmployees(Long departmentId) {
        return departmentRepository.getReferenceById(departmentId).getEmployees().stream()
                .filter(employee -> !employee.getName().equals("deleted"))
                .map(employeeService::convertToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaterialDto> getDepartmentMaterials(Long departmentId) {
        return departmentRepository.getReferenceById(departmentId).getMaterials().stream()
                .map(materialService::convertToMaterialDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProviderDto> getDepartmentProviders(Long departmentId) {
        return departmentRepository.getReferenceById(departmentId).getProviders().stream()
                .map(providerService::convertToProviderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getDepartmentCustomers(Long departmentId) {
        return departmentRepository.getReferenceById(departmentId).getCustomers().stream()
                .map(customerService::convertToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.getReferenceById(id);
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
        return convertToDepartmentDto(departmentRepository.getReferenceById(id));
    }

}
