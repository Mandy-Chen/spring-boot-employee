package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company addCompany(Company company) {
        if(company != null) {
            return companyRepository.save(company);
        }
        return null;
    }

    public Company updateCompany(Integer companyId, Company company) {
        if (companyId != null && company != null) {
            Optional<Company> optionalCompany = companyRepository.findById(companyId);
            if (optionalCompany.isPresent()) {
                Company companyInfo = optionalCompany.get();
                if (!StringUtils.isEmpty(companyInfo.getCompanyName())) {
                    companyInfo.setCompanyName(company.getCompanyName());
                }
                if (!StringUtils.isEmpty(companyInfo.getEmployeesNumber())) {
                    companyInfo.setEmployeesNumber(company.getEmployeesNumber());
                }
                if (!StringUtils.isEmpty(companyInfo.getEmployees())) {
                    companyInfo.setEmployees(companyInfo.getEmployees());
                }
                return companyRepository.save(companyInfo);
            }
        }
        return null;
    }

    public void deleteEmployeesOfCompanyById(Integer companyId) {
        if(companyId != null) {
            Optional<Company> optionalCompany = companyRepository.findById(companyId);
            if(optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                company.setEmployees(null);
                companyRepository.save(company);
            }
        }
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
