package com.pecodigos.gestao_vagas.modules.companies.controllers;

import com.pecodigos.gestao_vagas.modules.companies.dto.CreateJobDTO;
import com.pecodigos.gestao_vagas.modules.companies.entities.CompanyEntity;
import com.pecodigos.gestao_vagas.modules.companies.repositories.CompanyRepository;
import com.pecodigos.gestao_vagas.modules.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    @DisplayName("It should be able to create a new job")
    public void shouldBeAbleToCreateANewJob() throws Exception {

        var company = CompanyEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("email@company.com")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME").build();

        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder().benefits("BENEFITS_TEST").description("DESCRIPTION_TEST").level("LEVEL_TEST").build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }

    @Test
    public void shouldNotBeAbleToCreateANewJobIfCompanyNotFound() throws Exception {
        var createJobDTO = CreateJobDTO.builder().benefits("BENEFITS_TEST").description("DESCRIPTION_TEST").level("LEVEL_TEST").build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}

