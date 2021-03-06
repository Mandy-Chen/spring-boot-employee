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
public class EmployeeIntegrationTest {
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
    void should_get_all_employees_when_hit_get_all_employees_endpoint_given_nothing() throws Exception {
        //given
        Company company = new Company(1, "oocl", 0, emptyList());
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(0, "chen", 18, "female", 9999, savedCompany.getCompanyId());
        employeeRepository.save(employee);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("chen"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value("female"))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[0].companyId").value(savedCompany.getCompanyId()));
    }

    @Test
    void should_get_employee_by_id_when_hit_get_employee_by_id_endpoint_given_employee_id() throws Exception {
        //given
        Company company = new Company(1, "oocl", 0, emptyList());
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(0, "chen", 18, "female", 9999, savedCompany.getCompanyId());
        Employee savedEmployee=employeeRepository.save(employee);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/"+savedEmployee.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("chen"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value(9999))
                .andExpect(jsonPath("$.companyId").value(savedCompany.getCompanyId()));
    }


    @Test
    void should_get_employees_by_page_when_hit_get_employee_by_page_endpoint_given_page_and_pageSize() throws Exception {
        //given
        int page = 1;
        int pageSize = 2;
        Company company = new Company(1, "oocl", 0, emptyList());
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(1, "chen", 18, "female", 9999, savedCompany.getCompanyId());
        employeeRepository.save(employee);
        //when
        ResultActions resultActions = mockMvc.perform(get(String.format("/employees?page=%d&pageSize=%d", page, pageSize)));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.number").value(page-1))
                .andExpect(jsonPath("$.size").value(pageSize))
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].name").value("chen"))
                .andExpect(jsonPath("$.content[0].age").value(18))
                .andExpect(jsonPath("$.content[0].gender").value("female"))
                .andExpect(jsonPath("$.content[0].salary").value(9999))
                .andExpect(jsonPath("$.content[0].companyId").value(savedCompany.getCompanyId()));
    }

    @Test
    void should_get_employees_by_gender_when_hit_get_employee_by_gender_endpoint_given_female() throws Exception {
        //given
        String gender = "female";
        Company company = new Company(1, "oocl", 0, emptyList());
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(0, "chen", 18, "female", 9999, savedCompany.getCompanyId());
        employeeRepository.save(employee);
        //when
        ResultActions resultActions = mockMvc.perform(get(String.format("/employees?gender=%s", gender)));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("chen"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].gender").value(gender))
                .andExpect(jsonPath("$[0].salary").value(9999))
                .andExpect(jsonPath("$[0].companyId").value(savedCompany.getCompanyId()));
    }

    @Test
    void should_return_employee_when_hit_post_employee_endpoint_given_employee() throws Exception {
        //given
        Company savedCompany = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        //when
        ResultActions resultActions = mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"id\": 0, \"name\": \"test\", \"age\": 18, \"gender\": \"male\", \"salary\": 4000.0, \"companyId\": %s }",
                        savedCompany.getCompanyId())));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(4000.0))
                .andExpect(jsonPath("$.companyId").value(savedCompany.getCompanyId()));
    }

    @Test
    void should_return_employee_when_hit_put_update_employee_endpoint_given_employee() throws Exception {
        //given
        Company savedCompany = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, savedCompany.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(put("/employees/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"id\": %s, \"name\": \"updateName\", \"age\": 18, \"gender\": \"male\", \"salary\": 4000.0, \"companyId\": %s }",
                        employee.getId(), savedCompany.getCompanyId())));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value("updateName"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(4000.0))
                .andExpect(jsonPath("$.companyId").value(savedCompany.getCompanyId()));
    }

    @Test
    void should_status_is_ok_hit_delete_employee_by_id_endpoint_given_employee_id() throws Exception {
        //given
        Company savedCompany = companyRepository.save(new Company(1, "oocl", 0, emptyList()));
        Employee employee = employeeRepository.save(new Employee(0, "chen", 18, "female", 9999, savedCompany.getCompanyId()));
        //when
        ResultActions resultActions = mockMvc.perform(delete("/employees/" + employee.getId()));

        //then
        resultActions.andExpect(status().isOk());
        assertNull(employeeRepository.findById(employee.getId()).orElse(null));
    }

}
