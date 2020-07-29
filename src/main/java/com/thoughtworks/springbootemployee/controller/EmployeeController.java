package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    DataBase dataBase;

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        List<Employee> employees = dataBase.getEmployees();
        return employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().get();
    }

    @GetMapping
    public List<Employee> getEmployees(Integer pageNum, Integer pageSize, String gender) {
        List<Employee> employees = dataBase.getEmployees();
        if (gender != null)
            return employees.stream().filter(employee -> gender.equals(employee.getGender())).collect(Collectors.toList());
        else if (pageNum != null && pageSize != null) {
            return employees.subList((pageNum - 1) * pageSize, pageNum * pageSize);
        } else {
            return employees;
        }
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        List<Employee> employees = dataBase.getEmployees();
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployeeInfo(@PathVariable int employeeId, @RequestBody Employee changedEmployee) {
        List<Employee> employees = dataBase.getEmployees();
        Employee oldEmployee = employees.stream().filter(employee -> employeeId == employee.getId()).findFirst().get();
        if (oldEmployee != null) {
            employees.remove(oldEmployee);
            employees.add(changedEmployee);
        }
        return changedEmployee;
    }

    @DeleteMapping("{employeeId}")
    public String deleteEmployeeByEmployeeId(@PathVariable int employeeId) {
        List<Employee> employees = dataBase.getEmployees();
        Employee employee = employees.stream().filter(tempEmployee -> employeeId == tempEmployee.getId()).findFirst().get();
        employees.remove(employee);
        return "delete success";
    }
}
