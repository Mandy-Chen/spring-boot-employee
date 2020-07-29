package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompanyServiceTest {
    @Test
    void should_return_company_list_when_getAllCompanies() {
        //given

        //when
        CompanyService companyService = new CompanyService();
        List<Company> companies = companyService.findAllCompanies();
        //then
        assertNotNull(companies);
    }

    @Test
    void should_return_company_when_get_company_by_id_given_company_id() {
        //given
        int companyId = 1;
        CompanyService companyService = new CompanyService();

        //when
        Company company = companyService.getCompanyById(companyId);

        //then
        assertNotNull(company);

    }

}
