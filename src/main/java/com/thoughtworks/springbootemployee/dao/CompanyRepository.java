package com.thoughtworks.springbootemployee.dao;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {
    public List<Company> getAllCompanies() {
        return null;
    }
    public List<Company> getAllCompanies(int page,int pageSize) {
        return null;
    }

    public Company findById(int companyId) {
        return null;
    }

    public List<Employee> getEmployeesById(int companyId) {
        return null;
    }
}
