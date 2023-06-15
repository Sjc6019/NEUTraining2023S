package org.bill.demoproject.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void queryProjectList() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/queryProjectList")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"projectName\":\"\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mvcResult = mockMvc.perform(post("/queryProjectList")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"projectName\":\"error\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("0")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addProjectInfo() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/addProjectInfo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"projectName\":\"test\",\"projectContent\":\"test\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void modifyProjectInfo() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/modifyProjectInfo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":\"20ee9e067a71495287b4631aa821998b\",\"projectName\":\"unit-test\",\"projectContent\":\"unit-test\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteProjectById() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/deleteProjectById")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":\"6\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}