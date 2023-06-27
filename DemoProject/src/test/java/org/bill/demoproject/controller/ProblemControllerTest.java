package org.bill.demoproject.controller;

import jakarta.annotation.Resource;
import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.beans.OptionEntity;
import org.bill.demoproject.dao.entity.ProblemEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProblemControllerTest {

    @Resource
    private ProblemController problemController;
    private List<OptionEntity> optionEntityList = new ArrayList<>();
    @Test
    public void queryProblem() {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setProblemName("test");
        HttpResponseEntity httpResponseEntity = problemController.queryProblem(problemEntity);
        System.out.println(httpResponseEntity.getData());
        problemEntity.setProblemName("noresult");
        HttpResponseEntity httpResponseEntity1 = problemController.queryProblem(problemEntity);
        System.out.println(httpResponseEntity1.getData());
    }

    @Test
    public void addProblem() {
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setQuestionnaireId("dawfgfsdegrsrhgsrg");
        problemEntity.setProblemName("test-junit-add");
        problemEntity.setProblemType(2);
        problemEntity.setProblemOptions(optionEntityList);
        List<ProblemEntity> problemEntityList = new ArrayList<>();
        problemEntityList.add(problemEntity);
        HttpResponseEntity httpResponseEntity = problemController.addProblem(problemEntityList);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = problemController.queryProblem(problemEntity);
        String result = httpResponseEntity1.getData().toString();
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"questionnaireId\"")-1);
        System.out.println(id);
        problemEntity.setId(id);
    }
}