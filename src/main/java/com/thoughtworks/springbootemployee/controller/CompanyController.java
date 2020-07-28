package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping
    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, 1, 2));
        companies.add(new Company(2, 1, 2));
        companies.add(new Company(3,2,5));
        return companies;
    }


}
