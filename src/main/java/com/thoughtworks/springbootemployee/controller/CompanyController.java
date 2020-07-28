package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    DataBase dataBase;

    @GetMapping
    public List<Company> getAllCompanies() {
        return dataBase.getDataBase();
    }

    @GetMapping("/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable int companyId) {
        List<Company> companies = dataBase.getDataBase();
        for(Company company : companies) {
            if(company.getCompanyId() == companyId) {
                return company;
            }
        }
        return null;
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getAllEmployees(@PathVariable int companyId) {
        List<Company> companies = dataBase.getDataBase();
        for(Company company : companies) {
            if(company.getCompanyId() == companyId) {
                return company.getEmployees();
            }
        }
        return null;
    }





}
