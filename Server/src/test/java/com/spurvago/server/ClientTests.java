package com.spurvago.server;

import com.spurvago.components.Utils;
import com.spurvago.server.client.Client;
import com.spurvago.server.client.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientTests {
    private final List<Client> testClientList = new ArrayList<>();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void teardown() {
        clientRepository.deleteAll(testClientList);
    }

    // Testy Get
    @Test
    void GetRequest_getExistingClient_thenReturns200() throws Exception {
        Client testClient = initClient(
                new Client("Piotr", "Testowy", "test@test.test", "123456789"));

        mockMvc.perform(get("http://localhost:8081/api/client/{id}", testClient.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void GetRequest_getNonExistingClient_thenReturns404() throws Exception {
        mockMvc.perform(get("http://localhost:8081/api/client/123456789")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Testy Post
    @Test
    void PostRequest_createClient_thenReturns201() throws Exception {
        Client testClient = initClient(
                new Client("Jacek", "Testowy", "test1@test.test", "223456789"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void PostRequest_createClientWithWrongEmail_thenReturns422() throws Exception {
        Client testClient = initClient(
                new Client("Andrzej", "Testowy", "zly_email", "223456789"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithWrongPhoneNumber_thenReturns422() throws Exception {
        Client testClient = initClient(
                new Client("Jan", "Testowy", "email@asda.pl", "22345678912312313"));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8081/api/client")
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    // Testy Put
    @Test
    void PutRequest_updateClient_thenReturns200() throws Exception {
        Client testClient = initClient(
                new Client("Maciej", "Testowy", "mail@mail.pl", "223456789"));
        testClient.setFirstName("Maciek");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("http://localhost:8081/api/client/{id}", testClient.getId())
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Client initClient(Client client) {
        clientRepository.save(client);
        testClientList.add(client);
        return client;
    }
}
