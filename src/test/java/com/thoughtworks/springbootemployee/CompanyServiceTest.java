package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CompanyServiceTest {
//    @InjectMock

    @Test
    void should_return_company_list_when_getAllCompanies() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        //when
        CompanyService companyService = new CompanyService(mockedCompanyRepository);
        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "alibaba", 100, null));
        companies.add(new Company(2, "alibaba", 100, null));
        given(mockedCompanyRepository.getAllCompanies()).willReturn(companies);
        List<Company> actualCompanies = companyService.findAllCompanies();
        //then
        assertNotEquals(0, actualCompanies.size());
    }

    @Test
    void should_return_company_when_get_company_by_id_given_company_id() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);

        CompanyService companyService = new CompanyService(mockedCompanyRepository);
        Company company = new Company(1, "alibaba", 100, null);
        given(mockedCompanyRepository.findById(1)).willReturn(company);
        int companyId = 1;
        //when
        Company actualCompany = companyService.getCompanyById(companyId);
        //then
        assertEquals(company.getCompanyId(), actualCompany.getCompanyId());

    }

    @Test
    void should_return_all_employee_of_company_when_get_all_employee_given_company_id() {
        //given
        CompanyRepository mockedCompanyRepository = mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(mockedCompanyRepository);
        int companyId = 1;
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "alibaba1", 20, "male", 6000));
        employees.add(new Employee(2, "alibaba2", 20, "female", 6000));
        given(mockedCompanyRepository.getEmployeesById(1)).willReturn(employees);
        //when
        List<Employee> actualEmployees = companyService.getAllEmployeeOfCompany(companyId);
        //then
        assertEquals(employees, actualEmployees);

    }
}
