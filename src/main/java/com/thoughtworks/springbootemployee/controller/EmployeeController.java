package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getEmployees(int page, int pageSize) throws IllegalParameterException, OperationException {
        return employeeService.getAllEmployees(page, pageSize);
    }

    @GetMapping
    public List<Employee> getEmployees() throws OperationException {
        return employeeService.getAllEmployees();
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployees(String gender) throws IllegalParameterException, OperationException {
        return employeeService.getEmployeeByGender(gender);
    }


    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) throws IllegalParameterException, OperationException {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody EmployeeRequest employeeRequest) throws IllegalParameterException {
        return employeeService.addEmployee(employeeMapper.toEmployee(employeeRequest));
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeRequest employeeRequest) throws OperationException, IllegalParameterException {
        return employeeService.updateEmployee(employeeId, employeeMapper.toEmployee(employeeRequest));
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployeeById(@PathVariable int employeeId) throws IllegalParameterException {
        employeeService.deleteEmployeeById(employeeId);
    }
}
