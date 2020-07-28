package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Company;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
    public List<Company> getPaginateCompanies(@RequestParam Integer page, @RequestParam Integer pageSize) {
        if(page != null && pageSize != null) {
            List<Company> companies = dataBase.getCompanies();
            return companies.stream().filter(company ->
                    companies.indexOf(company) >= page - 1 && companies.indexOf(company) <= pageSize - 1
            ).collect(Collectors.toList());
        }
        return null;
    }


    @PostMapping
    public void addCompany(@RequestBody Company company) {
        List<Company> companies = dataBase.getCompanies();
        companies.add(company);
        dataBase.setCompanies(companies);
    }

    @PutMapping("/{companyId}")
    public void updateCompany(@RequestBody Company companyInfo) {
        List<Company> companies = dataBase.getCompanies();
        for(Company company : companies) {
            if(company.getCompanyId() == companyInfo.getCompanyId()) {
                company.setCompanyId(companyInfo.getCompanyId());
                company.setCompanyName(companyInfo.getCompanyName());
                company.setEmployees(companyInfo.getEmployees());
                company.setEmployeesNumber(companyInfo.getEmployeesNumber());
                companies.add(company);
                dataBase.setCompanies(companies);
            }
        }
    }

    @DeleteMapping("/{companyId}")
    public void deleteCompany(@PathVariable int companyId) {
        List<Company> companies = dataBase.getCompanies();
        for(int i=0;i<companies.size();i++) {
            if(companies.get(i).getCompanyId() == companyId) {
                companies.remove(i);
                i--;
                dataBase.setCompanies(companies);
            }
        }
    }
}
