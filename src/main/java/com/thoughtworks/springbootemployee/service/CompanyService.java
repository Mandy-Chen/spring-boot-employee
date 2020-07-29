package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {


    public List<Company> findAllCompanies() {
        DataBase dataBase = new DataBase();
        return dataBase.getCompanies();
    }

    public Company getCompanyById(int companyId) {
        DataBase dataBase=new DataBase();
        List<Company> companies=dataBase.getCompanies();
        return companies.stream().filter(company -> company.getCompanyId()==companyId).findFirst().get();
    }

    public List<Employee> getAllEmployeeOfCompany(int companyId) {
        return null;
    }
}
