package org.bill.demoproject.controller;

import jakarta.annotation.Resource;
import org.bill.demoproject.beans.AnswerEntity;
import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.dao.entity.AnswerSheetEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnswerSheetControllerTest {
    @Resource
    private AnswerSheetController answerSheetController;

    private List<AnswerEntity> answerEntityList = new ArrayList<>();

    @Test
    public void queryAnswerSheetInfo() {
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setAnswerUser("test");
        HttpResponseEntity httpResponseEntity = answerSheetController.queryAnswerSheetInfo(answerSheetEntity);
        System.out.println(httpResponseEntity.getData());
        answerSheetEntity.setAnswerUser("noresult");
        HttpResponseEntity httpResponseEntity1 = answerSheetController.queryAnswerSheetInfo(answerSheetEntity);
        System.out.println(httpResponseEntity1.getData());
    }

    @Test
    public void addAnswerSheetInfo() {
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setAnswerUser("test-junit-add");
        answerSheetEntity.setQuestionnaireId("dawfgfsdegrsrhgsrg");
        answerSheetEntity.setAnswer(answerEntityList);
        HttpResponseEntity httpResponseEntity = answerSheetController.addAnswerSheetInfo(answerSheetEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = answerSheetController.queryAnswerSheetInfo(answerSheetEntity);
        String result = httpResponseEntity1.getData().toString();
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"answerUser\"")-1);
        System.out.println(id);
        answerSheetEntity.setId(id);
        HttpResponseEntity httpResponseEntity2 = answerSheetController.deleteAnswerSheetById(answerSheetEntity);
        System.out.println(httpResponseEntity2.getData());
        answerSheetEntity.setAnswerUser(null);
        HttpResponseEntity httpResponseEntity3 = answerSheetController.addAnswerSheetInfo(answerSheetEntity);
        System.out.println(httpResponseEntity3.getData());
    }

    @Test
    public void deleteAnswerSheetById() {
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setAnswerUser("test-junit-delete");
        answerSheetEntity.setQuestionnaireId("dawfgfsdegrsrhgsrg");
        answerSheetEntity.setAnswer(answerEntityList);
        HttpResponseEntity httpResponseEntity = answerSheetController.addAnswerSheetInfo(answerSheetEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = answerSheetController.queryAnswerSheetInfo(answerSheetEntity);
        String result = httpResponseEntity1.getData().toString();
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"answerUser\"")-1);
        System.out.println(id);
        answerSheetEntity.setId(id);
        HttpResponseEntity httpResponseEntity2 = answerSheetController.deleteAnswerSheetById(answerSheetEntity);
        System.out.println(httpResponseEntity2.getData());
        answerSheetEntity.setAnswerUser(null);
        HttpResponseEntity httpResponseEntity3 = answerSheetController.addAnswerSheetInfo(answerSheetEntity);
        System.out.println(httpResponseEntity3.getData());
        System.out.println(httpResponseEntity3.getMessage());

    }
}