package bh.beca.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listByOwner() throws Exception {
        mockMvc.perform(get("/api/v1/customers/0/accounts"))
                .andExpect(content().json("[{\"id\":0,\"name\":\"Bonus Account\",\"total\":0.00000}]"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/v1/customers/0/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"initialCredit\": \"5\"}"))
                .andExpect(content().json("{\"id\":1,\"name\":\"Account 1\",\"total\":5}"));
    }
}