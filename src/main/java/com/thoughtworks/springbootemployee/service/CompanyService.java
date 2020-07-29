package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAllCompanies() {
        return companyRepository.getAllCompanies();
    }

    public Company getCompanyById(int companyId) {
        return companyRepository.findById(companyId);
    }

    public List<Employee> getAllEmployeeOfCompany(int companyId) {
        return companyRepository.getEmployeesById(companyId);
    }
}
