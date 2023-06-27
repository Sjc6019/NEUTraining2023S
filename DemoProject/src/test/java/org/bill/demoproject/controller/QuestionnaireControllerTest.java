package org.bill.demoproject.controller;

import jakarta.annotation.Resource;
import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionnaireControllerTest {

    @Resource
    private QuestionnaireController questionnaireController;

    @Test
    public void queryQuestionnaireInfo() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("test");
        HttpResponseEntity httpResponseEntity = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity.getData());
        questionnaireEntity.setQuestionnaireName("noresult");
        HttpResponseEntity httpResponseEntity1 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity1.getData());
    }

    @Test
    public void addQuestionnaireInfo() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("test-junit-add");
        questionnaireEntity.setProjectId("dawfgfsdegrsrhgsrg");
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
        questionnaireEntity.setQuestionnaireName(null);
        HttpResponseEntity httpResponseEntity3 = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity3.getData());
    }

    @Test
    public void modifyQuestionnaireInfo() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("test-junit-modify");
        questionnaireEntity.setProjectId("dawfgfsdegrsrhgsrg");
        questionnaireEntity.setStatus("1");
        HttpResponseEntity httpResponseEntity = questionnaireController.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity.getData());
        HttpResponseEntity httpResponseEntity1 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        String result = httpResponseEntity1.getData().toString();
        String id = result.substring(result.indexOf("\"id\":")+6,result.indexOf(", \"questionnaireName\"")-1);
        System.out.println(id);
        questionnaireEntity.setId(id);
        questionnaireEntity.setQuestionnaireName("test-junit-modify-modify");
        HttpResponseEntity httpResponseEntity2 = questionnaireController.modifyQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity2.getData());
        HttpResponseEntity httpResponseEntity3 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity3.getData());
        questionnaireEntity.setQuestionnaireName(null);
        HttpResponseEntity httpResponseEntity4 = questionnaireController.modifyQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity4.getData());
        HttpResponseEntity httpResponseEntity5 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity5.getData());
        HttpResponseEntity httpResponseEntity6 = questionnaireController.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(httpResponseEntity6.getData());
    }

    @Test
    public void deleteQuestionnaireById() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("test-junit-delete");
        questionnaireEntity.setProjectId("dawfgfsdegrsrhgsrg");
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
        HttpResponseEntity httpResponseEntity3 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity3.getData());
        questionnaireEntity.setQuestionnaireName(null);
        HttpResponseEntity httpResponseEntity4 = questionnaireController.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(httpResponseEntity4.getData());
        HttpResponseEntity httpResponseEntity5 = questionnaireController.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(httpResponseEntity5.getData());
    }
}