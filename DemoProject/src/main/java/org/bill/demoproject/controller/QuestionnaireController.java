package org.bill.demoproject.controller;

import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.bill.demoproject.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;
    @RequestMapping(value = "/queryQuestionnaireInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        System.out.println(questionnaireEntity);
        List<QuestionnaireEntity> hasQuestionnaire = questionnaireService.queryQuestionnaireInfo(questionnaireEntity);
        if (hasQuestionnaire.isEmpty()){
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("fail");
        }else {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(hasQuestionnaire);
            httpResponseEntity.setMessage("success");
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addQuestionnaireInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity1 = new HttpResponseEntity();
        System.out.println(questionnaireEntity);
        try {
            int result = questionnaireService.addQuestionnaireInfo(questionnaireEntity);
            if (result != 0) {
                httpResponseEntity1.setCode("666");
                httpResponseEntity1.setData(questionnaireEntity.getId());
                httpResponseEntity1.setMessage("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpResponseEntity1.setCode("0");
            httpResponseEntity1.setData(null);
            httpResponseEntity1.setMessage("fail");
        }
        return httpResponseEntity1;
    }

    @RequestMapping(value = "/modifyQuestionnaireInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity2 = new HttpResponseEntity();
        try {
            int result = questionnaireService.modifyQuestionnaireInfo(questionnaireEntity);
            if (result != 0) {
                httpResponseEntity2.setCode("666");
                httpResponseEntity2.setData(null);
                httpResponseEntity2.setMessage("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpResponseEntity2.setCode("0");
            httpResponseEntity2.setData(null);
            httpResponseEntity2.setMessage("fail");
        }
        return httpResponseEntity2;
    }

    @RequestMapping(value = "/deleteQuestionnaireById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity3 = new HttpResponseEntity();
        try {
            int result = questionnaireService.deleteQuestionnaireById(questionnaireEntity);
            if (result != 0) {
                httpResponseEntity3.setCode("666");
                httpResponseEntity3.setData(null);
                httpResponseEntity3.setMessage("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpResponseEntity3.setCode("0");
            httpResponseEntity3.setData(null);
            httpResponseEntity3.setMessage("fail");
        }
        return httpResponseEntity3;
    }
}
