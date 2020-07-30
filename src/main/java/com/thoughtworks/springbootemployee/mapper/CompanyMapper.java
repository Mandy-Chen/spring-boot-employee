package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company toCompany(CompanyRequest companyRequest) {
        return new Company(
                companyRequest.getCompanyId(),
                companyRequest.getCompanyName(),
                companyRequest.getEmployeesNumber(),
                companyRequest.getEmployees());
    }
}
