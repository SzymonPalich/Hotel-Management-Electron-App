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


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "init.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "teardown.sql", executionPhase = AFTER_TEST_METHOD)
public class ClientTests {
    @Autowired
    private MockMvc mockMvc;

    private final List<Client> testUsers = new ArrayList<>(List.of(
            new Client(1L, "Jacek", "Testowy", "j.testowy@test.test", "111111111"),
            new Client(2L, "Marcin", "Inny", "m.inny@test.test", null),
            new Client(3L, "Jan", "Kowalski", null, "333333333"),
            new Client(4L, "Szymon", "Jakiś", "s.jakis@test.test", "444444444"),
            new Client(5L, "Piotr", "Nowak", "p.nowak@test.test", "555555555")
    ));
    private final long NEXT_ID = 6L;

    // Testy Get
    @Test
    void GetRequest_getExistingClient_thenReturns200() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/client/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(Utils.asJsonString(getTestEntityById(1))));
    }

    @Test
    void GetRequest_getNonExistingClient_thenReturns404() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/client/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Testy Post
    @Test
    void PostRequest_createClient_thenReturns201() throws Exception {
        Client testClient = new Client(NEXT_ID, "Andrzej", "Nowy", "a.nowy@test.test", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(Utils.asJsonString(testClient)));
    }

    @Test
    void PostRequest_createClientWithWrongEmail_thenReturns422() throws Exception {
        Client testClient = new Client(NEXT_ID, "Andrzej", "Nowy", "wrong_email", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithWrongPhoneNumber_thenReturns422() throws Exception {
        Client testClient = new Client(NEXT_ID, "Andrzej", "Nowy", "a.nowy@test.test", "wrong_phone_number_666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createWithoutPhoneNumberAndEmail_thenReturns422() throws Exception {
        Client testClient = new Client(NEXT_ID, "Andrzej", "Nowy", null, "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithDuplicatedEmail_thenReturns422() throws Exception {
        Client testClient = new Client(NEXT_ID, "Andrzej", "Nowy", "j.testowy@test.test", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithDuplicatedPhoneNumber_thenReturns422() throws Exception {
        Client testClient = new Client(NEXT_ID, "Andrzej", "Nowy", "a.nowy@test.test", "111111111");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    // Testy Put
    @Test
    void PutRequest_updateClient_thenReturns200() throws Exception {
        Client testClient = getTestEntityById(3);
        testClient.setFirstName("UpdatedTest1");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8081/api/client/{id}", testClient.getId())
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(Utils.asJsonString(testClient)));
    }

    private Client getTestEntityById(int id) {
        return testUsers.get(id - 1);
    }
}
