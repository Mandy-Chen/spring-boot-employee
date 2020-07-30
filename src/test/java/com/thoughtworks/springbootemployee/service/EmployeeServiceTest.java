package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.EmployeeRepository;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository mockedEmployeeRepository;
    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_return_employees_list_when_getAllEmployees() {
        //when
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "mandy", 18, "female", 99999, 1));
        employees.add(new Employee(2, "Austin", 18, "male", 99999, 1));
        given(mockedEmployeeRepository.findAll()).willReturn(employees);
        List<Employee> actualEmployees = employeeService.findAllEmployees();
        //then
        assertEquals(2, actualEmployees.size());
    }
    @Test
    void should_return_employee_when_get_employ_by_id_given_employee_id() {
        //given
        Employee employee = new Employee(1, "mandy", 18, "female", 99999, 1);
        given(mockedEmployeeRepository.findById(1)).willReturn(Optional.of(employee));
        //when
        Employee actualEmploy = employeeService.getEmployeeById(1);
        //then
        assertEquals(Optional.of(employee).get().getId(), actualEmploy.getId());
    }

    @Test
    void should_return_employee_list_when_getAllEmployees_given_page_and_pageSize() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "mandy", 18, "female", 99999, 1));
        employees.add(new Employee(2, "Austin", 18, "male", 99999, 1));
        given(mockedEmployeeRepository.findAll(PageRequest.of(1, 2))).willReturn(new PageImpl<Employee>(employees));
        //when
        Page<Employee> actualEmployees = employeeService.getAllEmployees(1, 2);
        //then
        assertEquals(new PageImpl<Employee>(employees), actualEmployees);
    }
    @Test
    void should_return_all_male_employees_when_getAllEmployees_given_gender_is_male() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2, "Austin", 18, "male", 99999, 1));
        employees.add(new Employee(3, "other", 18, "male", 99999, 1));
        given(mockedEmployeeRepository.findAllByGender("male")).willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.getEmployeeByGender("male");
        //then
        assertEquals(employees, actualEmployees);
    }
    @Test
    void should_return_employ_when_add_employee_given_employee() {
        //given
        Employee employee = new Employee(1, "mandy", 18, "female", 99999, 1);
        given(mockedEmployeeRepository.save(employee)).willReturn(employee);
        //when
        Employee addedEmployee = employeeService.addEmployee(employee);
        //then
        assertEquals(employee, addedEmployee);
    }
    @Test
    void should_return_employee_when_update_employee_given_employee() {
        //given
        Employee employee = new Employee(1, "mandy", 18, "female", 99999, 1);
        Employee updateEmployee = new Employee(1, "mandy", 18, "female", 66666, 1);
        given(mockedEmployeeRepository.findById(1)).willReturn(Optional.of(employee));
        given(mockedEmployeeRepository.save(isA(Employee.class))).willReturn(updateEmployee);
        //when
        Employee updateEmployee1 = employeeService.updateEmployee(1, updateEmployee);
        //the
        assertEquals(updateEmployee1, updateEmployee);
    }
    @Test
    void should_return_nothing_when_delete_employee_given_employeeId() {
        //given
        Employee employee = new Employee(1, "mandy", 18, "female", 66666, 1);
        given(mockedEmployeeRepository.findById(1)).willReturn(Optional.of(employee));
        //when
        employeeService.deleteEmployeeById(1);
        //then
        verify(mockedEmployeeRepository,times(1)).deleteById(1);
    }
}
