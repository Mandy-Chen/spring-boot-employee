package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.dao.EmployeeRepository;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {
    EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
    EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

    @Test
    void should_return_employees_list_when_getAllEmployees() {
        //when
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "mandy", 18, "female",99999));
        employees.add(new Employee(2, "Austin", 18, "male",99999));
        given(mockedEmployeeRepository.findAll()).willReturn(employees);
        List<Employee> actualEmployees = employeeService.findAllCompanies();
        //then
        assertEquals(2, actualEmployees.size());
    }
    @Test
    void should_return_employee_when_get_employ_by_id_given_employee_id() {
        //given
        Employee employee = new Employee(1, "mandy", 18, "female",99999);
        given(mockedEmployeeRepository.findById(1)).willReturn(Optional.of(employee));
        //when
        Employee actualEmploy = employeeService.getEmployeeById(1);
        //then
        assertEquals(Optional.of(employee).get().getId(), actualEmploy.getId());

    }
}
