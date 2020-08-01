package com.thoughtworks.springbootemployee.integationtest;

import com.thoughtworks.springbootemployee.dao.CompanyRepository;
import com.thoughtworks.springbootemployee.dao.EmployeeRepository;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static java.util.Collections.emptyList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;

    @AfterEach
    public void afterEach() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_get_all_companies_when_hit_get_all_companies_endpoint_given_nothing() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, company.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyId").value(company.getCompanyId()))
                .andExpect(jsonPath("$[0].companyName").value(company.getCompanyName()))
                .andExpect(jsonPath("$[0].employeesNumber").value(company.getEmployeesNumber()))
                .andExpect(jsonPath("$[0].employees[0].id").isNumber())
                .andExpect(jsonPath("$[0].employees[0].name").value("chen"))
                .andExpect(jsonPath("$[0].employees[0].age").value(18))
                .andExpect(jsonPath("$[0].employees[0].gender").value("female"))
                .andExpect(jsonPath("$[0].employees[0].salary").value(9999))
                .andExpect(jsonPath("$[0].employees[0].companyId").value(company.getCompanyId()));
    }

    @Test
    void should_get_company_by_id_when_hit_get_company_by_id_endpoint_given_company_id() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, company.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/" + company.getCompanyId()));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").value(company.getCompanyId()))
                .andExpect(jsonPath("$.companyName").value(company.getCompanyName()))
                .andExpect(jsonPath("$.employeesNumber").value(company.getEmployeesNumber()))
                .andExpect(jsonPath("$.employees[0].id").isNumber())
                .andExpect(jsonPath("$.employees[0].name").value("chen"))
                .andExpect(jsonPath("$.employees[0].age").value(18))
                .andExpect(jsonPath("$.employees[0].gender").value("female"))
                .andExpect(jsonPath("$.employees[0].salary").value(9999))
                .andExpect(jsonPath("$.employees[0].companyId").value(company.getCompanyId()));
    }

    @Test
    void should_get_companies_by_page_when_hit_get_companies_by_page_endpoint_given_page_and_pageSize() throws Exception {
        //given
        int page = 1;
        int pageSize = 2;
        Company company = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, company.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(get(String.format("/companies?page=%d&pageSize=%d", page, pageSize)));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.number").value(page-1))
                .andExpect(jsonPath("$.size").value(pageSize))
                .andExpect(jsonPath("$.content[0].companyId").value(company.getCompanyId()))
                .andExpect(jsonPath("$.content[0].companyName").value(company.getCompanyName()))
                .andExpect(jsonPath("$.content[0].employeesNumber").value(company.getEmployeesNumber()))
                .andExpect(jsonPath("$.content[0].employees[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].employees[0].name").value("chen"))
                .andExpect(jsonPath("$.content[0].employees[0].age").value(18))
                .andExpect(jsonPath("$.content[0].employees[0].gender").value("female"))
                .andExpect(jsonPath("$.content[0].employees[0].salary").value(9999))
                .andExpect(jsonPath("$.content[0].employees[0].companyId").value(company.getCompanyId()));
    }



    @Test
    void should_return_company_when_hit_put_update_company_endpoint_given_company_and_companyId() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, company.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(put("/companies/" + company.getCompanyId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format(" {\n" +
                                "            \"companyId\": %s,\n" +
                                "                \"companyName\": \"csuft\",\n" +
                                "                \"employeesNumber\": 100,\n" +
                                "                \"employees\": [\n" +
                                "            {\n" +
                                "                \"id\": %s,\n" +
                                "                    \"name\": \"test\",\n" +
                                "                    \"age\": 19,\n" +
                                "                    \"gender\": \"male\",\n" +
                                "                    \"salary\": 4000.0,\n" +
                                "                    \"companyId\": %s\n" +
                                "            }\n" +
                                "    ]\n" +
                                "        }",
                        company.getCompanyId(), employee.getId(), company.getCompanyId())));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").value(company.getCompanyId()))
                .andExpect(jsonPath("$.companyName").value("csuft"))
                .andExpect(jsonPath("$.employeesNumber").value(100))
                .andExpect(jsonPath("$.employees[0].id").value(employee.getId()))
                .andExpect(jsonPath("$.employees[0].name").value("test"))
                .andExpect(jsonPath("$.employees[0].age").value(19))
                .andExpect(jsonPath("$.employees[0].gender").value("male"))
                .andExpect(jsonPath("$.employees[0].salary").value(4000.0))
                .andExpect(jsonPath("$.employees[0].companyId").value(company.getCompanyId()));
    }


    @Test
    void should_status_is_ok_hit_delete_company_by_id_endpoint_given_company_id() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        //when
        ResultActions resultActions = mockMvc.perform(delete("/companies/" + company.getCompanyId()));
        //then
        resultActions.andExpect(status().isOk());
        assertNull(companyRepository.findById(company.getCompanyId()).orElse(null));
    }


    @Test
    void should_return_company_when_hit_post_company_endpoint_given_company() throws Exception {
        //given
        Company company = new Company(1, "oocl", 100, emptyList());
        //when
        ResultActions resultActions = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"companyId\": %s, \"companyName\": \"%s\", \"employeesNumber\": %s }",
                        company.getCompanyId(), company.getCompanyName(), company.getEmployeesNumber())));
        //then
        resultActions
                .andExpect(jsonPath("$.companyId").isNumber())
                .andExpect(jsonPath("$.companyName").value(company.getCompanyName()))
                .andExpect(jsonPath("$.employeesNumber").value(company.getEmployeesNumber()));
    }

    @Test
    void should_return_employees_when_hit_get_employees_endpoint_given_company_id() throws Exception {
        //given
        Company company = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, company.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/" + company.getCompanyId() + "/employees"));

        //then
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("chen"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[0].companyId").value(company.getCompanyId()));
    }

}
