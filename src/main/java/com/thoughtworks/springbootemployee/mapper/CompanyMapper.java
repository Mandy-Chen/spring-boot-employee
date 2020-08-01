package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.beans.BeanUtils;
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

    public CompanyResponse toCompanyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company, companyResponse);
        return companyResponse;
    }

}
