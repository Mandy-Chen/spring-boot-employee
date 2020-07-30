package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequst;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeMapperTest {
    @Test
    void should_get_employee_when_mapper_given_employee_request() {
        //given
        EmployeeMapper employeeMapper = new EmployeeMapper();
        EmployeeRequst employeeRequst = new EmployeeRequst(0, "chen", 18, "female", 9999, 1);
        //when
        Employee employee = employeeMapper.toEmployee(employeeRequst);
        //then
        assertEquals(employeeRequst.getId(), employee.getId());
        assertEquals(employeeRequst.getAge(), employee.getAge());
        assertEquals(employeeRequst.getCompanyId(), employee.getCompanyId());
        assertEquals(employeeRequst.getGender(), employee.getGender());
        assertEquals(employeeRequst.getName(), employee.getName());
        assertEquals(employeeRequst.getSalary(), employee.getSalary());

    }
}
