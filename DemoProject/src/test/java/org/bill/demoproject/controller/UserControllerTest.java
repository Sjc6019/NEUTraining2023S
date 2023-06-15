package org.bill.demoproject.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.annotation.Resource;
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
class UserControllerTest {

    @Resource
    private UserController userController;

    @Test
    void userLogin() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword("test");
        HttpResponseEntity httpResponseEntity = userController.userLogin(userEntity);
        System.out.println(httpResponseEntity);
    }


    @Test
    void queryUserList() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        HttpResponseEntity httpResponseEntityUser = userController.queryUserList(userEntity);
        System.out.println(httpResponseEntityUser);
    }

    @Test
    void addUserInfo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testAdd");
        userEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity = userController.addUserInfo(userEntity);
        HttpResponseEntity httpResponseEntity1 = userController.queryUserList(userEntity);
        ArrayList<UserEntity> resultList = (ArrayList<UserEntity>) httpResponseEntity1.getData();
        String result = resultList.get(0).toString();
        System.out.println(result);
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"username\"")-1);
        System.out.println(id);
        userEntity.setId(id);
        HttpResponseEntity httpResponseEntity2 = userController.deleteUserById(userEntity);
        System.out.println(httpResponseEntity2.getData());
    }

    @Test
    void modifyUserInfo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testModify");
        userEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity = userController.addUserInfo(userEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = userController.queryUserList(userEntity);
        ArrayList<UserEntity> resultList = (ArrayList<UserEntity>) httpResponseEntity1.getData();
        String result = resultList.get(0).toString();
        System.out.println(result);
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"username\"")-1);
        System.out.println(id);
        userEntity.setId(id);
    }

    @Test
    void deleteUserById() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testDelete");
        userEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity = userController.addUserInfo(userEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = userController.queryUserList(userEntity);
        ArrayList<UserEntity> resultList = (ArrayList<UserEntity>) httpResponseEntity1.getData();
        String result = resultList.get(0).toString();
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"username\"")-1);
        System.out.println(id);
        userEntity.setId(id);
        HttpResponseEntity httpResponseEntity2 = userController.deleteUserById(userEntity);
        System.out.println(httpResponseEntity2.getData());
    }
}