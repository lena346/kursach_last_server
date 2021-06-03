package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.FitnessSpringServer;
import com.elena.fitnessserver.models.Client;
import com.elena.fitnessserver.repositories.ClientRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = FitnessSpringServer.class)
@AutoConfigureMockMvc
@Transactional

class ClientControllerTest {
    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    MockMvc mvc;

    @Test
    void create() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("first_NameA", "Елена");
            jsonObject.put("last_NameA", "Шашкина");
            jsonObject.put("birthdayA", LocalDate.of(2000,10,10));
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonObject.toString())
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void read() {
        try{
            long id = 2;
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/clients"+id))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(body);
                        assertEquals(clientRepository.findById(id).get().getId(), jsonObject.getLong("id"));
                        assertEquals(clientRepository.findById(id).get().getFirstNameClient(), jsonObject.getString("first_NameA"));
                        assertEquals(clientRepository.findById(id).get().getLastNameClient(), jsonObject.getInt("last_NameA"));
                        assertEquals(clientRepository.findById(id).get().getDateClient(), jsonObject.getString("dateA"));

                    })
                    .andReturn();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    void update() {
        try {
            long id = 1L;
            JSONObject jsonObject = new JSONObject();
            Optional<Client> client = clientRepository.findById(id);
            jsonObject.put("first_NameA",client.get().getFirstNameClient());
            jsonObject.put("id",client.get().getId());
            jsonObject.put("last_NameA",client.get().getLastNameClient());
            jsonObject.put("dateA",client.get().getDateClient());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/clients/{id}"+id)
                    .content(jsonObject.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try{
            long id =1;
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/clients/{id}"+id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}