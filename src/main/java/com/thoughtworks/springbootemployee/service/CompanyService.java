package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.exception.IllegalParameterException;
import com.thoughtworks.springbootemployee.exception.OperationException;
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

    //todo
    public Company getCompanyById(int companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getAllEmployeeOfCompany(int companyId) throws OperationException {
        Company company = companyRepository.findById(companyId).orElse(null);
        if (Objects.nonNull(company)) {
            return company.getEmployees();
        }
        throw new OperationException("Can't found company");
    }

    public Page<Company> getAllCompanies(Integer page, Integer pageSize) throws IllegalParameterException {
        if (page < 1 || pageSize < 0) {
            throw new IllegalParameterException("page need to more than zero and page size can't less than zero");
        }
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public Company addCompany(Company company) throws OperationException {
        if (company != null) {
            return companyRepository.save(company);
        }
        throw new OperationException("Add company fail!");
    }

    public Company updateCompany(Integer companyId, Company company) throws IllegalParameterException {
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
        throw new IllegalParameterException("companyId can't be null or don't post a empty info of company");
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
