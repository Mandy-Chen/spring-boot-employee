package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.EmployeeRepository;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId).get();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int employeeId, Employee updateEmployee) throws OperationException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (Objects.isNull(employee)) {
            throw new OperationException("Can't found the employee");
        } else {
            employee.setAge(updateEmployee.getAge());
            employee.setGender(updateEmployee.getGender());
            employee.setName(updateEmployee.getName());
            employee.setSalary(updateEmployee.getSalary());
            return employeeRepository.save(employee);
        }
    }

    public void deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Page<Employee> getAllEmployees(int page, int pageSize) throws IllegalParameterException {
        if (page < 1 || pageSize < 0) {
            throw new IllegalParameterException("page need to more than zero and page size can't less than zero");
        }
        return employeeRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


}
