package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.FitnessSpringServer;
import com.elena.fitnessserver.models.Client;
import com.elena.fitnessserver.models.Program;
import com.elena.fitnessserver.repositories.ClientRepository;
import com.elena.fitnessserver.repositories.ProgramRepository;
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

class ProgramControllerTest {
    @Autowired
    public ProgramRepository programRepository;

    @Autowired
    MockMvc mvc;

    @Test
    void create() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("NameA", "Фитнес");
            jsonObject.put("priceA", 80000);
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/programs")
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
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/programs"+id))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(body);
                        assertEquals(programRepository.findById(id).get().getId(), jsonObject.getLong("id"));
                        assertEquals(programRepository.findById(id).get().getName(), jsonObject.getString("NameA"));
                        assertEquals(programRepository.findById(id).get().getPrice(), jsonObject.getInt("priceA"));

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
            Optional<Program> program = programRepository.findById(id);
            jsonObject.put("NameA",program.get().getName());
            jsonObject.put("id",program.get().getId());
            jsonObject.put("priceA",program.get().getPrice());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/programs/{id}"+id)
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
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/programs/{id}"+id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}