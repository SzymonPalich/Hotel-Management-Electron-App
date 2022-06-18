package com.spurvago.server.client;

import com.jayway.jsonpath.JsonPath;
import com.spurvago.components.Utils;
import com.spurvago.database.Client;
import com.spurvago.server.client.models.ClientFM;
import com.spurvago.server.client.models.ClientVM;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testy klienta
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = "init.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = "teardown.sql", executionPhase = AFTER_TEST_METHOD)
public class ClientTests {
    //<editor-fold desc="Wartości z bazy">
    private final List<ClientFM> testUsers = new ArrayList<>(List.of(
            new ClientFM( "Jacek", "Testowy", "j.testowy@test.test", "111111111"),
            new ClientFM("Marcin", "Inny", "m.inny@test.test", null),
            new ClientFM( "Jan", "Kowalski", null, "333333333"),
            new ClientFM( "Szymon", "Jakiś", "s.jakis@test.test", "444444444"),
            new ClientFM("Piotr", "Nowak", "p.nowak@test.test", "555555555")

    ));
    private final long NEXT_ID = 6L;
    //</editor-fold>

    //<editor-fold desc="Konfiguracja">
    @LocalServerPort
    private int port;
    private final String HTTP_ADDRESS = "http://localhost:" + port + "/api/client";
    @Autowired
    private MockMvc mockMvc;
    //</editor-fold>


    @Test
    void GetListRequest_getAllClients_thenReturns200() throws Exception {
        mockMvc.perform(get(HTTP_ADDRESS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(5));
    }


    @Test
    void GetRequest_getExistingClient_thenReturns200() throws Exception {
        mockMvc.perform(get(HTTP_ADDRESS + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void GetRequest_getNonExistingClient_thenReturns404() throws Exception {
        mockMvc.perform(get(HTTP_ADDRESS + "/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    //</editor-fold>

    //<editor-fold desc="Testy GetList">

    @Test
    void GetListRequest_FindUser_thenReturns200() throws Exception {
        ClientFM testClient = getTestEntityById(1);
        String testClientInfo = testClient.getFirstName() + " " + testClient.getLastName() + " " + testClient.getEmail();

        mockMvc.perform(get(HTTP_ADDRESS)
                        .param("search", testClientInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void GetListRequest_nonExistingClients_thenReturns200() throws Exception {
        mockMvc.perform(get(HTTP_ADDRESS)
                        .param("search", "ojoj nie ma")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(0));
    }
// U NAS NIE MA NIE MA LIMITU DLUGOSCI SEARCHA
//    @Test
//    void GetListRequest_exceedSearchWordLimit_thenReturns422() throws Exception {
//        mockMvc.perform(get(HTTP_ADDRESS)
//                        .param("search", "ojoj jest zbyt dużo słów")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isUnprocessableEntity());
//    }
    //</editor-fold>

    //<editor-fold desc="Testy Post">
    @Test
    void PostRequest_createClient_thenReturns201() throws Exception {
        ClientFM testClient = new ClientFM("Jan", "Dzban", "asdas@wp.pl", "321321321");
        ClientVM expectedClient = new ClientVM(NEXT_ID, "Jan", "Dzban", "asdas@wp.pl", "321321321");
        mockMvc.perform(MockMvcRequestBuilders
                        .post(HTTP_ADDRESS)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(Utils.asJsonString(expectedClient)));
    }

    @Test
    void PostRequest_createClientWithWrongEmail_thenReturns422() throws Exception {
        ClientFM testClient = new ClientFM("Andrzej", "Nowy", "wrong_email", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(HTTP_ADDRESS)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithWrongPhoneNumber_thenReturns422() throws Exception {
        ClientFM testClient = new ClientFM("Andrzej", "Nowy", "a.nowy@test.test", "wrong_phone_number_666666666");
        mockMvc.perform(MockMvcRequestBuilders
                        .post(HTTP_ADDRESS)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createNullPhoneNumberAndEmail_thenReturns422() throws Exception {
        ClientFM testClient = new ClientFM("Andrzej", "Nowy", null, null);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(HTTP_ADDRESS)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithDuplicatedEmail_thenReturns422() throws Exception {
        ClientFM testClient = new ClientFM( "Andrzej", "Nowy", "j.testowy@test.test", "666666666");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(HTTP_ADDRESS)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PostRequest_createClientWithDuplicatedPhoneNumber_thenReturns422() throws Exception {
        ClientFM testClient = new ClientFM( "Andrzej", "Nowy", "a.nowy@test.test", "111111111");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(HTTP_ADDRESS)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
    //</editor-fold>

    //<editor-fold desc="Testy Update">
    @Test
    void PutRequest_updateClient_thenReturns200() throws Exception {
        ClientFM testClient = getTestEntityById(4);
        testClient.setFirstName("UpdatedTest1");
        mockMvc.perform(MockMvcRequestBuilders
                        .put(HTTP_ADDRESS + "/{id}", 4)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(Utils.asJsonString(testClient)));
    }


    @Test
    void PutRequest_updateClientRemoveEmail_thenReturns200() throws Exception {
        ClientFM testClient = getTestEntityById(4);
        testClient.setEmail("");

        mockMvc.perform(MockMvcRequestBuilders
                        .put(HTTP_ADDRESS + "/{id}", 4)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void PutRequest_updateClientDuplicateEmailAndPhoneNumber_thenReturns422() throws Exception {
        ClientFM testClient = getTestEntityById(3);
        testClient.setEmail(getTestEntityById(1).getEmail());
        System.out.println("getTestEntityById(1).getEmail(): "+ getTestEntityById(1).getEmail());
        testClient.setPhoneNumber(getTestEntityById(1).getPhoneNumber());
        System.out.println("getTestEntityById(1).getPhoneNumber(): "+ getTestEntityById(1).getPhoneNumber());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(HTTP_ADDRESS + "/{id}", 3)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void PutRequest_updateClientNullPhoneNumberAndEmail_thenReturns422() throws Exception {
        ClientFM testClient = getTestEntityById(3);
        testClient.setPhoneNumber(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(HTTP_ADDRESS + "/{id}", 1)
                        .content(Utils.asJsonString(testClient))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity());
    }
    //</editor-fold>

    //<editor-fold desc="Testy Delete">
    @Test
    void DeleteRequest_deleteExistingClient_thenReturns203And201() throws Exception {
        ClientFM testClient = getTestEntityById(1);

        mockMvc.perform(delete(HTTP_ADDRESS + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get(HTTP_ADDRESS + "/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void DeleteRequest_deleteNonExistingClient_thenReturns404() throws Exception {
        mockMvc.perform(delete(HTTP_ADDRESS + "/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    //</editor-fold>

    private ClientFM getTestEntityById(int id) {
        return testUsers.get(id - 1);
    }
}
