package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Util.DataBase;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {


    public List<Company> findAllCompanies() {
        DataBase dataBase = new DataBase();
        return dataBase.getCompanies();
    }
}
