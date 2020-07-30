package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable int companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getAllEmployees(@PathVariable int companyId) throws OperationException {
        return companyService.getAllEmployeeOfCompany(companyId);
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getAllCompanies(int page, int pageSize) throws IllegalParameterException {
        return companyService.getAllCompanies(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) throws OperationException {
        return companyService.addCompany(company);
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company updateCompany(@PathVariable Integer companyId, @RequestBody Company company) throws IllegalParameterException {
        return companyService.updateCompany(companyId, company);
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeesOfCompanyById(@PathVariable Integer companyId) {
        companyService.deleteEmployeesOfCompanyById(companyId);
    }
}
