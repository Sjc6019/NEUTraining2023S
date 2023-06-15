package org.bill.demoproject.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.dao.entity.UserEntity;
import org.bill.demoproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userLogin() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/admin/userLogin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mvcResult = mockMvc.perform(post("/admin/userLogin")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"admin\",\"password\":\"error\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("0")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void queryUserList() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/admin/queryUserList")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"admin\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mvcResult = mockMvc.perform(post("/admin/queryUserList")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"test03\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mvcResult = mockMvc.perform(post("/admin/queryUserList")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void addUserInfo() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/admin/addUserInfo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"test09\",\"password\":\"123123\",\"startTime\":1685610377000,\"stopTime\":1688137598000}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("666")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void modifyUserInfo() {
        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(post("/admin/modifyUserInfo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":2,\"username\":\"test-mod\",\"password\":\"123123\",\"startTime\":1685610377000,\"stopTime\":1688137598000}")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("10")))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteUserById() {
    }
}