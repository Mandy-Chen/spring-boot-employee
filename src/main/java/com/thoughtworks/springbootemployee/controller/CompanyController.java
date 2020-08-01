package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
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

    CompanyService companyService;
    CompanyMapper companyMapper;

    public CompanyController(CompanyMapper companyMapper, CompanyService companyService) {
        this.companyMapper = companyMapper;
        this.companyService = companyService;
    }

    @GetMapping("/{companyId}")
    public Company getCompanyByCompanyId(@PathVariable int companyId) throws OperationException {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getAllEmployees(@PathVariable int companyId) throws OperationException {
        return companyService.getAllEmployeeOfCompany(companyId);
    }

    @GetMapping
    public List<Company> getAllCompanies() throws OperationException {
        return companyService.getAllCompanies();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getAllCompanies(int page, int pageSize) throws IllegalParameterException {
        return companyService.getAllCompanies(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody CompanyRequest companyRequest) throws OperationException {
        return companyService.addCompany(companyMapper.toCompany(companyRequest));
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public Company updateCompany(@PathVariable Integer companyId, @RequestBody CompanyRequest companyRequest) throws IllegalParameterException, OperationException {
        return companyService.updateCompany(companyId, companyMapper.toCompany(companyRequest));
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeesOfCompanyById(@PathVariable Integer companyId) throws IllegalParameterException {
        companyService.deleteEmployeesOfCompanyById(companyId);
    }
}
