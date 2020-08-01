package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyMapperTest {
    @Test
    void should_get_company_when_mapper_given_company_request() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        CompanyRequest companyRequest = new CompanyRequest(0, "oocl", 100, emptyList());
        //when
        Company company = companyMapper.toCompany(companyRequest);
        //then
        assertEquals(companyRequest.getCompanyId(), company.getCompanyId());
        assertEquals(companyRequest.getCompanyName(), company.getCompanyName());
        assertEquals(companyRequest.getEmployees(), company.getEmployees());
    }
    @Test
    void should_get_company_when_mapper_given_company_response() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        CompanyResponse companyResponse = new CompanyResponse(0, "oocl", 100, emptyList());
        //when
        Company company = companyMapper.toCompany(companyResponse);
        //then
        assertEquals(companyResponse.getCompanyId(), company.getCompanyId());
        assertEquals(companyResponse.getCompanyName(), company.getCompanyName());
        assertEquals(companyResponse.getEmployees(), company.getEmployees());
    }
}
