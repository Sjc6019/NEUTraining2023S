package org.bill.demoproject.controller;

import jakarta.annotation.Resource;
import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.dao.entity.ProjectEntity;
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
class ProjectControllerTest {
    @Resource
    private ProjectController projectController;
    @Test
    void queryProjectList() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("test");
        HttpResponseEntity httpResponseEntity = projectController.queryProjectList(projectEntity);

    }

    @Test
    void addProjectInfo() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("test-add");

        HttpResponseEntity httpResponseEntity = projectController.addProjectInfo(projectEntity);

    }

    @Test
    void modifyProjectInfo() {

    }

    @Test
    void deleteProjectById() {

    }
}