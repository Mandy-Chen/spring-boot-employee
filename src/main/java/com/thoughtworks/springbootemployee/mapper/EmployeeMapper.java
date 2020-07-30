package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEmployee(EmployeeRequest employeeRequest) {
        return new Employee(employeeRequest.getId(), employeeRequest.getName(),
                employeeRequest.getAge(),
                employeeRequest.getGender(),
                employeeRequest.getSalary(),
                employeeRequest.getCompanyId());
    }
}
