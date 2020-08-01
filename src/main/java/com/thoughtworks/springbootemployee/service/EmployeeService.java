package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.EmployeeRepository;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public Employee getEmployeeById(Integer employeeId) throws IllegalParameterException, OperationException {
        if(Objects.isNull(employeeId)){
            throw new IllegalParameterException("employeeId does not exist!");
        }
        Employee employee=employeeRepository.findById(employeeId).orElse(null);
        if (Objects.isNull(employee)){
            throw new OperationException("Can't found employee!");
        }
        return employee;
    }

    public Employee addEmployee(Employee employee) throws IllegalParameterException {
        if (Objects.isNull(employee)){
            throw new IllegalParameterException("employee can't be empty!");
        }
        return employee;
    }

    public Employee updateEmployee(Integer employeeId, Employee updateEmployee) throws OperationException, IllegalParameterException {
        if(Objects.isNull(employeeId)){
            throw new IllegalParameterException("employeeId can't be empty!");
        }
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (Objects.isNull(employee)) {
            throw new OperationException("Can't found the employee");
        } else {
            employee.setAge(updateEmployee.getAge());
            employee.setGender(updateEmployee.getGender());
            employee.setName(updateEmployee.getName());
            employee.setSalary(updateEmployee.getSalary());
            return  employeeRepository.save(employee);
        }
    }

    public void deleteEmployeeById(Integer employeeId) throws IllegalParameterException {
        if(Objects.isNull(employeeId)){
            throw new IllegalParameterException("employeeId can't be empty!");
        }
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> findAllEmployees() throws OperationException {
        List<Employee> employees=employeeRepository.findAll();
        if(Objects.isNull(employees)){
            throw new OperationException("Nothing was found！");
        }
        return employees;
    }

    public List<Employee> getEmployeeByGender(String gender) throws IllegalParameterException, OperationException {
        if(Objects.isNull(gender)){
            throw new IllegalParameterException("gender can't be empty!");
        }
        List<Employee> employees=employeeRepository.findAllByGender(gender);
        if(employees.isEmpty()){
            throw new OperationException("Nothing was found！");
        }
        return employees;
    }

    public Page<Employee> getAllEmployees(int page, int pageSize) throws IllegalParameterException, OperationException {
        if (page < 0 || pageSize < 0) {
            throw new IllegalParameterException("page need to more than zero and page size can't less than zero");
        }
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(page-1 , pageSize));
        if (Objects.isNull(employees)){
            throw new OperationException("Nothing was found！");
        }
        return employees;
    }

    public List<Employee> getAllEmployees() throws OperationException {
        List<Employee> employees=employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new OperationException("Nothing was found！");
        }
        return (employees);
    }


}
