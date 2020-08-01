package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.thoughtworks.springbootemployee.mapper.CompanyMapper.toCompanyResponse;


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
    public CompanyResponse getCompanyByCompanyId(@PathVariable int companyId) throws OperationException {
        return toCompanyResponse(companyService.getCompanyById(companyId));
    }

    @GetMapping("/{companyId}/employees")
    public List<EmployeeResponse> getAllEmployees(@PathVariable int companyId) throws OperationException {
        return companyService.getAllEmployeeOfCompany(companyId).stream().map(EmployeeMapper::toEmployeeResponse).collect(Collectors.toList());
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() throws OperationException {
        return companyService.getAllCompanies().stream().map(CompanyMapper::toCompanyResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Company> getAllCompanies(int page, int pageSize) throws IllegalParameterException, OperationException {
        return companyService.getAllCompanies(page, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) throws OperationException {
        return toCompanyResponse(companyService.addCompany(companyMapper.toCompany(companyRequest)));
    }

    @PutMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse updateCompany(@PathVariable Integer companyId, @RequestBody CompanyRequest companyRequest) throws IllegalParameterException, OperationException {
        return toCompanyResponse(companyService.updateCompany(companyId, companyMapper.toCompany(companyRequest)));
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployeesOfCompanyById(@PathVariable Integer companyId) throws IllegalParameterException {
        companyService.deleteEmployeesOfCompanyById(companyId);
    }
}
