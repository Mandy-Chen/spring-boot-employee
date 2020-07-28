package com.thoughtworks.springbootemployee.Util;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBase {
    public List<Company> getDataBase() {
        List<Company> companies = new ArrayList<>();
        List<Employee> employeesA = new ArrayList<>();
        employeesA.add(new Employee(4, "alibaba1", 20, "male", 6000));
        employeesA.add(new Employee(6, "alibaba2", 19, "male", 8000));
        employeesA.add(new Employee(11, "alibaba3", 19, "female", 7000));
        Company companyA = new Company(1, "alibaba", 100, employeesA);
        companies.add(companyA);

        List<Employee> employeesB = new ArrayList<>();
        employeesB.add(new Employee(1, "tengxun1", 20, "male", 6000));
        employeesB.add(new Employee(2, "tengxun2", 19, "male", 8000));
        employeesB.add(new Employee(3, "tengxun3", 19, "female", 7000));
        Company companyB = new Company(1, "tengxun", 100, employeesB);
        companies.add(companyB);

        List<Employee> employeesC = new ArrayList<>();
        employeesC.add(new Employee(1, "huawei1", 20, "male", 6000));
        employeesC.add(new Employee(2, "huawei2", 19, "male", 8000));
        employeesC.add(new Employee(3, "huawei3", 19, "female", 7000));
        Company companyC = new Company(1, "huawei", 100, employeesC);
        companies.add(companyC);


        return companies;
    }
}
