package org.bill.demoproject.controller;

import jakarta.annotation.Resource;
import org.bill.demoproject.beans.AnswerEntity;
import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class QuestionnaireControllerTest {
    @Resource
    private QuestionnaireController questionnaireController;
    @Test
    void queryQuestionnaireInfo() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("test");
        HttpResponseEntity httpResponseEntity = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity.getData());
        questionnaireEntity.setQuestionnaireName("noresult");
        HttpResponseEntity httpResponseEntity1 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity1.getData());
    }

    @Test
    void addQuestionnaireInfo() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("test-junit-add");
        questionnaireEntity.setProjectId("dawfgfg");
        questionnaireEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        String result = httpResponseEntity1.getData().toString();
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"questionnaireName\"")-1);
        System.out.println(id);
        questionnaireEntity.setId(id);
        HttpResponseEntity httpResponseEntity2 = questionnaireController.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(httpResponseEntity2.getData());
        questionnaireEntity.setStatus(null);
        HttpResponseEntity httpResponseEntity3 = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity3.getData());

    }

    @Test
    void modifyQuestionnaireInfo() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId("testID");
        questionnaireEntity.setProjectId("dawfgfsd");
        questionnaireEntity.setStatus("1");

        HttpResponseEntity httpResponseEntity = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        String result = httpResponseEntity1.getData().toString();
        System.out.println(result);
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"projectId\"")-1);
        System.out.println(id);
        questionnaireEntity.setId(id);
        questionnaireEntity.setQuestionnaireName("testmodify");
        HttpResponseEntity httpResponseEntity2 = questionnaireController.modifyQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity2.getData());
        HttpResponseEntity httpResponseEntity3 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity3.getData());

        questionnaireEntity.setQuestionnaireName("testmodifytestmodifytestmodifytestmodifytestmodifytestmodifytestmodifytestmodifytestmodifytestmodifytestmodifytestmodify");
        questionnaireEntity.setProjectId(null);
        HttpResponseEntity httpResponseEntity4 = questionnaireController.modifyQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity4.getData());
        HttpResponseEntity httpResponseEntity5 = questionnaireController.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(httpResponseEntity5.getData());

    }

    @Test
    void deleteQuestionnaireById() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId("testID");
        questionnaireEntity.setProjectId("dawfgfsd");
        questionnaireEntity.setQuestionnaireName("testdelete");

        questionnaireEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        String result = httpResponseEntity1.getData().toString();
        System.out.println(result);
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"projectId\"")-1);
        System.out.println(id);
        questionnaireEntity.setId(id);

        HttpResponseEntity httpResponseEntity2 = questionnaireController.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(httpResponseEntity2.getData());


        questionnaireEntity.setId(null);
        HttpResponseEntity httpResponseEntity4 = questionnaireController.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(httpResponseEntity4.getData());
    }
}