package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {
    @Test
    void should_get_employee_when_mapper_given_employee_request() {
        //given
        EmployeeMapper employeeMapper = new EmployeeMapper();
        EmployeeRequest employeeRequest = new EmployeeRequest(0, "chen", 18, "female", 9999, 1);
        //when
        Employee employee = employeeMapper.toEmployee(employeeRequest);
        //then
        assertEquals(employeeRequest.getId(), employee.getId());
        assertEquals(employeeRequest.getAge(), employee.getAge());
        assertEquals(employeeRequest.getCompanyId(), employee.getCompanyId());
        assertEquals(employeeRequest.getGender(), employee.getGender());
        assertEquals(employeeRequest.getName(), employee.getName());
        assertEquals(employeeRequest.getSalary(), employee.getSalary());

    }
    @Test
    void should_get_employee_when_mapper_given_employee_response() {
        //given
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Employee employee = new Employee(0, "chen", 18, "female", 9999, 1);
        //when
        EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employee);
        //then
        assertEquals(employeeResponse.getId(), employee.getId());
        assertEquals(employeeResponse.getAge(), employee.getAge());
        assertEquals(employeeResponse.getCompanyId(), employee.getCompanyId());
        assertEquals(employeeResponse.getGender(), employee.getGender());
        assertEquals(employeeResponse.getName(), employee.getName());
        assertEquals(employeeResponse.getSalary(), employee.getSalary());

    }
}
