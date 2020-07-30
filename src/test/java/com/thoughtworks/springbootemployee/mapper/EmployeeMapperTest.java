package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
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
}
