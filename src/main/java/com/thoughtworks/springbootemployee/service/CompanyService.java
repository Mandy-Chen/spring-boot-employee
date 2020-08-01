package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    CompanyRepository companyRepository;
    CompanyMapper companyMapper;
    EmployeeMapper employeeMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    public Company getCompanyById(int companyId) throws OperationException {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (Objects.isNull(company)) {
            throw new OperationException("Can't found company!");
        }
        return company;
    }

    public List<Employee> getAllEmployeeOfCompany(int companyId) throws OperationException {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (Objects.isNull(company)) {
            throw new OperationException("Can't found company!");
        }
        return company.getEmployees();
    }

    public Page<Company> getAllCompanies(Integer page, Integer pageSize) throws IllegalParameterException, OperationException {
        if (page < 0 || pageSize < 0) {
            throw new IllegalParameterException("page need to more than zero and page size can't less than zero!");
        }
        Page<Company> companies = companyRepository.findAll(PageRequest.of(page-1 , pageSize));
        if(Objects.isNull(companyRepository.findAll())){
            throw new OperationException("Can't found company11111111!");
        }
        return companies;
    }


    public Company addCompany(Company company) throws OperationException {
        if (Objects.nonNull(company)) {
            return companyRepository.save(company);
        }
        throw new OperationException("Add company fail!");
    }

    public Company updateCompany(Integer companyId, Company company) throws IllegalParameterException, OperationException {
        if (Objects.nonNull(companyId) && Objects.nonNull(company)) {
            Company beforeCompany = companyRepository.findById(companyId).orElse(null);
            if (Objects.nonNull(beforeCompany)) {
                beforeCompany.setCompanyId(company.getCompanyId());
                beforeCompany.setCompanyName(company.getCompanyName());
                beforeCompany.setEmployees(company.getEmployees());
                beforeCompany.setEmployeesNumber(company.getEmployeesNumber());
                return companyRepository.save(beforeCompany);
            } else {
                throw new OperationException("Can't found company!");
            }
        }
        throw new IllegalParameterException("companyId can't be null or don't post a empty info of company!");
    }

    public void deleteEmployeesOfCompanyById(Integer companyId) throws IllegalParameterException {
        if (Objects.nonNull(companyId)) {
            companyRepository.deleteById(companyId);
        } else {
            throw new IllegalParameterException("companyId can't be null!");
        }
    }

    public List<Company> getAllCompanies() throws OperationException {
        if (companyRepository.findAll().isEmpty()) {
            throw new OperationException("Nothing was found！");
        }
        return companyRepository.findAll();
    }
}
