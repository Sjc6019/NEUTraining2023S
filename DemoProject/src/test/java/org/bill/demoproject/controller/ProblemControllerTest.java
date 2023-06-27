package org.bill.demoproject.controller;

import jakarta.annotation.Resource;
import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.beans.OptionEntity;
import org.bill.demoproject.dao.entity.ProblemEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class ProblemControllerTest {
    @Resource
    private ProblemController problemController;
    @Test
    void queryProblem() {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setProblemName("test");
        HttpResponseEntity httpResponseEntity = problemController.queryProblem(problemEntity);
        System.out.println(httpResponseEntity.getData());
        problemEntity.setProblemName("noresult");
        HttpResponseEntity httpResponseEntity1 = problemController.queryProblem(problemEntity);
        System.out.println(httpResponseEntity1.getData());

    }

    @Test
    void addProblem() {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setQuestionnaireId("dawfgfsdegrsrhgsrg");
        problemEntity.setProblemName("test-junit-add");
        problemEntity.setProblemType(2);
        List<OptionEntity> optionEntityList = new ArrayList<>();
        problemEntity.setProblemOptions(optionEntityList);
        List<ProblemEntity> problemEntityList = new ArrayList<>();
        problemEntityList.add(problemEntity);

        HttpResponseEntity httpResponseEntity = problemController.addProblem(problemEntityList);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = problemController.queryProblem(problemEntity);
        System.out.println(httpResponseEntity1.getData());

        problemEntity.setQuestionnaireId(null);
        HttpResponseEntity httpResponseEntity2 = problemController.addProblem(problemEntityList);
        System.out.println(httpResponseEntity2.getData());


    }
}