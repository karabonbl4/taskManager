package com.taskManager.service.impl;

import com.taskManager.model.entity.*;
import com.taskManager.model.repository.DepartmentRepository;
import com.taskManager.service.EmployeeService;
import com.taskManager.service.UserService;
import com.taskManager.service.converter.*;
import com.taskManager.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private DepartmentConverter departmentConverter;
    @Mock
    private UserService userService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private DateConverter dateConverter;
    @Mock
    private CustomerConverter customerConverter;
    @Mock
    private MaterialConverter materialConverter;
    @Mock
    private EmployeeConverter employeeConverter;
    @Mock
    private ProviderConverter providerConverter;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void save() {
        final Department department = mock(Department.class);
        final List<Department> departments = new ArrayList<>();
        final DepartmentDto departmentDto = mock(DepartmentDto.class);
        final User authUser = mock(User.class);
        authUser.setUsername("anyUsername");
        departments.add(department);
        when(userService.getAuthUser()).thenReturn(authUser);
        when(departmentRepository.findByEmployees_User_Username(userService.getAuthUser().getUsername())).thenReturn(departments);
        lenient().when(department.getName()).thenReturn("TITLE");
        lenient().when(departmentDto.getName()).thenReturn("TITLE");

        boolean actual = departmentService.save(departmentDto);

        assertFalse(actual);

        departments.remove(department);
        when(departmentConverter.convertToDepartment(departmentDto)).thenReturn(department);

        boolean actualSave = departmentService.save(departmentDto);

        assertTrue(actualSave);
    }

    @Test
    void findByAuthUsername() {
        final List<Department> departments = mock(List.class);
        final User user = mock(User.class);
        when(userService.getAuthUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("username");
        when(departmentRepository.findByEmployees_User_Username("username")).thenReturn(departments);

        final List<Department> returnedDepartments= departmentService.findByAuthUsername();

        assertNotNull(returnedDepartments);
        assertEquals(departments, returnedDepartments);
        verify(departmentRepository).findByEmployees_User_Username("username");
    }

    @Test
    void getDepartmentsDto() {
        final List<Department> departments = new ArrayList<>();
        final Department department = mock(Department.class);
        final List<DepartmentDto> departmentsDto = new ArrayList<>();
        final DepartmentDto departmentDto = mock(DepartmentDto.class);
        final User user = mock(User.class);
        departments.add(department);
        departmentsDto.add(departmentDto);
        when(userService.getAuthUser()).thenReturn(user);
        when(user.getUsername()).thenReturn("username");
        when(departmentRepository.findByEmployees_User_Username("username")).thenReturn(departments);

        when(departmentConverter.convertToDepartmentDto(department)).thenReturn(departmentDto);
        final List<DepartmentDto> returnedDepartment = departmentService.getDepartmentsDto();

        assertNotNull(returnedDepartment);
        assertEquals(departmentsDto, returnedDepartment);
    }

    @Test
    void getDepartmentEmployees() {
        final Department department = mock(Department.class);
        final List<Employee> employees = new ArrayList<>();
        final List<EmployeeDto> employeesDto = new ArrayList<>();
        final Employee employee = mock(Employee.class);
        final EmployeeDto employeeDto = mock(EmployeeDto.class);
        employees.add(employee);
        employeesDto.add(employeeDto);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(department.getEmployees()).thenReturn(employees);
        when(employee.getName()).thenReturn("job_title");
        when(employeeConverter.convertToEmployeeDto(employee)).thenReturn(employeeDto);

        final List<EmployeeDto> returnedListEmployees = departmentService.getDepartmentEmployees(1L);

        assertNotNull(returnedListEmployees);
        assertEquals(employeesDto, returnedListEmployees);
    }

    @Test
    void getDepartmentMaterials() {
        final Department department = mock(Department.class);
        final List<Material> materials = new ArrayList<>();
        final List<MaterialDto> materialsDto = new ArrayList<>();
        final Material material = mock(Material.class);
        final MaterialDto materialDto = mock(MaterialDto.class);
        materials.add(material);
        materialsDto.add(materialDto);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(department.getMaterials()).thenReturn(materials);
        when(material.getName()).thenReturn("some_name");
        when(materialConverter.convertToMaterialDto(material)).thenReturn(materialDto);

        final List<MaterialDto> returnedListMaterials = departmentService.getDepartmentMaterials(1L);

        assertNotNull(returnedListMaterials);
        assertEquals(materialsDto, returnedListMaterials);
    }

    @Test
    void getDepartmentProviders() {
        final Department department = mock(Department.class);
        final List<Provider> providers = new ArrayList<>();
        final List<ProviderDto> providersDto = new ArrayList<>();
        final Provider provider = mock(Provider.class);
        final ProviderDto providerDto = mock(ProviderDto.class);
        providers.add(provider);
        providersDto.add(providerDto);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(department.getProviders()).thenReturn(providers);
        when(provider.getName()).thenReturn("some_name");
        when(providerConverter.convertToProviderDto(provider)).thenReturn(providerDto);

        final List<ProviderDto> returnedListProviders = departmentService.getDepartmentProviders(1L);

        assertNotNull(returnedListProviders);
        assertEquals(providersDto, returnedListProviders);
    }

    @Test
    void getDepartmentCustomers() {
        final Department department = mock(Department.class);
        final List<Customer> customers = new ArrayList<>();
        final List<CustomerDto> customersDto = new ArrayList<>();
        final Customer customer = mock(Customer.class);
        final CustomerDto customerDto = mock(CustomerDto.class);
        customers.add(customer);
        customersDto.add(customerDto);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(department.getCustomers()).thenReturn(customers);
        when(customer.getName()).thenReturn("some_name");
        when(customerConverter.convertToCustomerDto(customer)).thenReturn(customerDto);

        final List<CustomerDto> returnedListCustomers = departmentService.getDepartmentCustomers(1L);

        assertNotNull(returnedListCustomers);
        assertEquals(customersDto, returnedListCustomers);
    }

    @Test
    void update() {
        final Department department = mock(Department.class);
        final Department customerWithChanges = mock(Department.class);
        final DepartmentDto departmentDto = mock(DepartmentDto.class);
        when(departmentDto.getId()).thenReturn(1L);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(department.getName()).thenReturn("Title");
        when(departmentDto.getName()).thenReturn("Title");
        when(department.getLocation()).thenReturn("Location");
        when(departmentDto.getLocation()).thenReturn("Location");
        boolean actual = departmentService.update(departmentDto);

        assertFalse(actual);
        when(department.getName()).thenReturn("Title");
        when(departmentDto.getName()).thenReturn("title");
        when(department.getLocation()).thenReturn("Location");
        when(departmentDto.getLocation()).thenReturn("location");
        boolean actualTrue = departmentService.update(departmentDto);

        assertTrue(actualTrue);
    }

    @Test
    void getWorkdayToday() {
        WorkDayWithDepartmentIdDto workDayWithDepartmentIdDto = new WorkDayWithDepartmentIdDto();
        final Date workdayToday = mock(Date.class);
        workDayWithDepartmentIdDto.setDate(workdayToday);
        when(dateConverter.convertLocalToDate(LocalDate.now())).thenReturn(workdayToday);

        final WorkDayWithDepartmentIdDto actual = departmentService.getWorkdayToday();

        assertNotNull(actual);
        assertEquals(workDayWithDepartmentIdDto, actual);
    }

    @Test
    void containEmployee() {
        final EmployeeDto employeeDto = mock(EmployeeDto.class);
        final Employee employee = mock(Employee.class);
        final Department department = mock(Department.class);
        final List<Employee> employees = new ArrayList<>();
        final User user = mock(User.class);
        employees.add(employee);
        when(employeeDto.getDepartmentId()).thenReturn(1L);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(department.getEmployees()).thenReturn(employees);
        when(employee.getUser()).thenReturn(user);
        when(user.getEmail()).thenReturn("some_email");
        when(employeeDto.getEmail()).thenReturn("some_email");

        final boolean actualTrue = departmentService.containEmployee(employeeDto);
        assertTrue(actualTrue);

        when(employeeDto.getEmail()).thenReturn("other_email");

        final boolean actualFalse = departmentService.containEmployee(employeeDto);
        assertFalse(actualFalse);
    }

    @Test
    void findById() {
        final DepartmentDto departmentDto = mock(DepartmentDto.class);
        final Department department = mock(Department.class);
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);
        when(departmentConverter.convertToDepartmentDto(department)).thenReturn(departmentDto);

        final DepartmentDto actual = departmentService.findById(1L);

        assertNotNull(actual);
        assertEquals(departmentDto, actual);
        verify(departmentRepository).getReferenceById(1L);
    }
}