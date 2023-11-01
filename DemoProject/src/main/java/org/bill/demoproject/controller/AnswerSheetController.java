package org.bill.demoproject.controller;

import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.entity.AnswerSheetEntity;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.bill.demoproject.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class AnswerSheetController {
    @Autowired
    private AnswerSheetService answerSheetService;
    @RequestMapping(value = "/queryAnswerSheetInfo",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryAnswerSheetInfo(@RequestBody AnswerSheetEntity answerSheetEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        System.out.println(answerSheetEntity);

        List<AnswerSheetEntity> hasAnswerSheet = answerSheetService.queryAnswerSheet(answerSheetEntity);
        if (hasAnswerSheet.isEmpty()){
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("fail");
        }else {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(hasAnswerSheet);
            httpResponseEntity.setMessage("success");
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addAnswerSheetInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addAnswerSheetInfo(@RequestBody AnswerSheetEntity answerSheetEntity) {
        HttpResponseEntity httpResponseEntity1 = new HttpResponseEntity();
        System.out.println(answerSheetEntity);
        try {
            int result = answerSheetService.addAnswerSheet(answerSheetEntity);
            if (result != 0) {
                httpResponseEntity1.setCode("666");
                httpResponseEntity1.setData(null);
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

    @RequestMapping(value = "/deleteAnswerSheetById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteAnswerSheetById(@RequestBody AnswerSheetEntity answerSheetEntity) {
        HttpResponseEntity httpResponseEntity3 = new HttpResponseEntity();
        try {
            int result = answerSheetService.deleteAnswerSheetById(answerSheetEntity);
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
