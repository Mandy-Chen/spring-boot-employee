package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Company;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    DataBase dataBase;

    @GetMapping("/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable int companyId) {
        List<Company> companies = dataBase.getCompanies();
        for(Company company : companies) {
            if(company.getCompanyId() == companyId) {
                return company;
            }
        }
        return null;
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getAllEmployees(@PathVariable int companyId) {
        List<Company> companies = dataBase.getCompanies();
        for(Company company : companies) {
            if(company.getCompanyId() == companyId) {
                return company.getEmployees();
            }
        }
        return null;
    }

    @GetMapping
    public List<Company> getAllCompanies(Integer page, Integer pageSize) {
        if(page != null && pageSize != null) {
            List<Company> companies = dataBase.getCompanies();
            return companies.subList((page-1)*(pageSize), page*pageSize);
        }
        return dataBase.getCompanies();
    }


    @PostMapping
    public void addCompany(@RequestBody Company company) {
        List<Company> companies = dataBase.getCompanies();
        companies.add(company);
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@RequestBody Company companyInfo) {
        List<Company> companies = dataBase.getCompanies();
        Company updateCompany = companies.stream().filter(company -> company.getCompanyId() == companyInfo.getCompanyId()).findFirst().get();
        updateCompany.setCompanyId(companyInfo.getCompanyId());
        updateCompany.setEmployees(companyInfo.getEmployees());
        updateCompany.setEmployeesNumber(companyInfo.getEmployeesNumber());
        updateCompany.setCompanyName(companyInfo.getCompanyName());
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable int companyId) {
        List<Company> companies = dataBase.getCompanies();
        Company deleteCompany = companies.stream().filter(company -> company.getCompanyId() == companyId).findFirst().get();
        deleteCompany.getEmployees().clear();
    }
}
