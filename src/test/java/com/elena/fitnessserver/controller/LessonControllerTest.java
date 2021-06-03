package com.elena.fitnessserver.controller;

import com.elena.fitnessserver.FitnessSpringServer;
import com.elena.fitnessserver.models.Client;
import com.elena.fitnessserver.models.Instructor;
import com.elena.fitnessserver.models.Lesson;
import com.elena.fitnessserver.repositories.ClientRepository;
import com.elena.fitnessserver.repositories.InstructorRepository;
import com.elena.fitnessserver.repositories.LessonRepository;
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

class LessonControllerTest {
    @Autowired
    public LessonRepository lessonRepository;

    @Autowired
    MockMvc mvc;

    @Test
    void create() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dateA", LocalDate.of(2021,06,02));
            this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/lessons")
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
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/lessons"+id))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(body);
                        assertEquals(lessonRepository.findById(id).get().getId(), jsonObject.getLong("id"));
                        assertEquals(lessonRepository.findById(id).get().getDate(), jsonObject.getString("dateA"));

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
            Optional<Lesson> lesson = lessonRepository.findById(id);
            jsonObject.put("id",lesson.get().getId());
            jsonObject.put("dateA",lesson.get().getDate());
            this.mvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/lessons/{id}"+id)
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
            this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/lessons/{id}"+id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}