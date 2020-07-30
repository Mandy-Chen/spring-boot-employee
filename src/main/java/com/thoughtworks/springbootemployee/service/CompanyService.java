package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
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

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int companyId) {
        return companyRepository.findById(companyId).get();
    }

    public List<Employee> getAllEmployeeOfCompany(int companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (Objects.nonNull(company)) {
            return company.getEmployees();
        }
        return null;
    }

    public Page<Company> getAllCompanies(Integer page, Integer pageSize) {
        if (page < 1 || pageSize < 0) {
            return null;
        }
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Company addCompany(Company company) {
        if (company != null) {
            return companyRepository.save(company);
        }
        return null;
    }

    public Company updateCompany(Integer companyId, Company company) {
        if (companyId != null && company != null) {
            Company beforeCompany = companyRepository.findById(companyId).orElse(null);
            if (Objects.nonNull(beforeCompany)) {
                beforeCompany.setCompanyId(company.getCompanyId());
                beforeCompany.setCompanyName(company.getCompanyName());
                beforeCompany.setEmployees(company.getEmployees());
                beforeCompany.setEmployeesNumber(company.getEmployeesNumber());
                return companyRepository.save(beforeCompany);
            }
        }
        return null;
    }

    public void deleteEmployeesOfCompanyById(Integer companyId) {
        if (Objects.nonNull(companyId)) {
            companyRepository.deleteById(companyId);
        }
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
