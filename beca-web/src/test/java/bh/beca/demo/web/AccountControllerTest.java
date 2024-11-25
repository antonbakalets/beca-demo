package bh.beca.demo.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bh.beca.demo.dto.AccountCreateDto;
import bh.beca.demo.dto.AccountDto;
import bh.beca.demo.service.AccountService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AccountService accountService;

    @Test
    void create() throws Exception {
        AccountDto output = new AccountDto();
        output.setId(124L);
        output.setName("Account Test");
        output.setTotal(new BigDecimal("5"));

        when(accountService.createAccount(eq(0), any(AccountCreateDto.class))).thenReturn(output);

        mockMvc.perform(post("/api/v1/customers/0/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"initialCredit\": \"5\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Account Test\",\"total\":5}"));
    }
}