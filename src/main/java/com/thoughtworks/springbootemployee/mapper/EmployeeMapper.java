package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequst;
import com.thoughtworks.springbootemployee.model.Employee;

public class EmployeeMapper {
    public Employee toEmployee(EmployeeRequst employeeRequst) {
        return new Employee(employeeRequst.getId(), employeeRequst.getName(),
                employeeRequst.getAge(),
                employeeRequst.getGender(),
                employeeRequst.getSalary(),
                employeeRequst.getCompanyId());
    }
}
