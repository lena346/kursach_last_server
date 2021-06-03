package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.FitnessSpringServer;
import com.elena.fitnessserver.models.Client;
import com.elena.fitnessserver.models.Instructor;
import com.elena.fitnessserver.repositories.ClientRepository;
import com.elena.fitnessserver.repositories.InstructorRepository;
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

class InstructorControllerTest {
    @Autowired
    public InstructorRepository instructorRepository;

    @Autowired
    MockMvc mvc;

    @Test
    void create() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("first_NameA", "Олег");
            jsonObject.put("last_NameA", "Гришатов");
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/instructors")
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
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/instructors"+id))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(body);
                        assertEquals(instructorRepository.findById(id).get().getId(), jsonObject.getLong("id"));
                        assertEquals(instructorRepository.findById(id).get().getFirstName(), jsonObject.getString("first_NameA"));
                        assertEquals(instructorRepository.findById(id).get().getLastName(), jsonObject.getInt("last_NameA"));
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
            Optional<Instructor> instructor = instructorRepository.findById(id);
            jsonObject.put("first_NameA",instructor.get().getFirstName());
            jsonObject.put("id",instructor.get().getId());
            jsonObject.put("last_NameA",instructor.get().getLastName());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/instructors/{id}"+id)
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
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/instructors/{id}"+id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}