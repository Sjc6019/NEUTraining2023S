package org.bill.demoproject.controller;

import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.entity.ProblemEntity;
import org.bill.demoproject.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/queryProblem",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryProblem(@RequestBody ProblemEntity problemEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        System.out.println(problemEntity);
        List<ProblemEntity> hasProblem = problemService.queryProblem(problemEntity);
        if (hasProblem.isEmpty()){
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("fail");
        }else {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(hasProblem);
            System.out.println(hasProblem);
            System.out.println(hasProblem.get(0).getProblemOptions());
            httpResponseEntity.setMessage("success");
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addProblem",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addProblem(@RequestBody List<ProblemEntity> problemEntity) {
        HttpResponseEntity httpResponseEntity1 = new HttpResponseEntity();
        System.out.println("addProblem:");
        System.out.println(problemEntity);

        problemService.deleteProblemByQuestionnaireId(problemEntity.get(0).getQuestionnaireId());
        for (ProblemEntity problemEntity1 : problemEntity) {
            problemEntity1.setId(UUIDUtil.getOneUUID());
        }
        try {
            int result = problemService.addProblem(problemEntity);
            if (result != 0) {
                httpResponseEntity1.setCode("666");
//                httpResponseEntity1.setData("{\"id\":\""+problemEntity.getId()+"\"}");
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

}
