package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public List<Employee> getEmployees(@RequestParam(required = false, defaultValue = "0") int pageNum, @RequestParam(required = false, defaultValue = "0") int pageSize, @RequestParam(required = false, defaultValue = "0") String gender) {
        List<Employee> employees = dataBase.getEmployees();
        if (!"0".equals(gender))
            return employees.stream().filter(employee -> gender.equals(employee.getGender())).collect(Collectors.toList());
        if (0 == pageNum && 0 == pageSize) {
            return employees;
        } else {
            List<Employee> employeesPage = employees.stream().filter(employee -> {
                int index = employees.indexOf(employee);
                return index >= pageNum * pageSize - pageSize && index <= pageNum * pageSize - 1;
            }).collect(Collectors.toList());
            return employeesPage;
        }
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        List<Employee> employees = dataBase.getEmployees();
        employees.add(employee);
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
    public void deleteEmployeeByEmployeeId(@PathVariable int employeeId) {
        List<Employee> employees = dataBase.getEmployees();
        Employee employee = employees.stream().filter(tempEmployee -> employeeId == tempEmployee.getId()).findFirst().get();
        employees.remove(employee);
    }
}
