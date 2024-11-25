package bh.beca.demo.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bh.beca.demo.dto.CustomerSummaryDto;
import bh.beca.demo.service.CustomerService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CustomerService customerService;

    @Test
    void summaryByCustomer() throws Exception {
        when(customerService.getSummary(54)).thenReturn(Optional.of(CustomerSummaryDto.builder().id(54).build()));

        mockMvc.perform(get("/api/v1/customers/54"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":54,\"firstName\":null,\"lastName\":null,\"accounts\":null}"));
    }

    @Test
    void customerNotFound() throws Exception {
        when(customerService.getSummary(54)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/customers/54"))
                .andExpect(status().isNotFound());
    }
}