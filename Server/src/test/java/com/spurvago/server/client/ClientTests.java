package com.spurvago.server.client;

import com.spurvago.components.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Test1, Test1, test1@test.test, 111111111
// Test2, Test2, test2@test.test, 222222222
// Test3, Test3, test3@test.test, 333333333
// Test4, Test4, test4@test.test, 444444444
// Test5, Test5, test5@test.test, 555555555

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "init.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "teardown.sql", executionPhase = AFTER_TEST_METHOD)
public class ClientTests {
    @Autowired
    private MockMvc mockMvc;

    // Testy Get
    @Test
    void GetRequest_getExistingClient_thenReturns200() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void GetRequest_getNonExistingClient_thenReturns404() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/client/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Testy Post
    @Test
    void PostRequest_createClient_thenReturns201() throws Exception {
        Client testClient = new Client("Test6", "Test6", "test6@test.test", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void PostRequest_createClientWithWrongEmail_thenReturns422() throws Exception {
        Client testClient = new Client("Test6", "Test6", "wrong_email", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithWrongPhoneNumber_thenReturns422() throws Exception {
        Client testClient = new Client("Test6", "Test6", "test6@test.test", "wrong_phone_number_666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createWithoutPhoneNumberAndEmail_thenReturns422() throws Exception {
        Client testClient = new Client("Test6", "Test6", "", "");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithDuplicatedEmail_thenReturns422() throws Exception {
        Client testClient = new Client("Test6", "Test6", "test5@test.test", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    // Testy Put
    @Test
    void PutRequest_updateClient_thenReturns200() throws Exception {
        Client testClient = new Client("Test1", "Test1", "test1@test.test", "111111111");
        testClient.setFirstName("UpdatedTest1");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8081/api/client/1")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
