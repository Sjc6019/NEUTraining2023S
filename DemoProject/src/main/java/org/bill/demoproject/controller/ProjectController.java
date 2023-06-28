package org.bill.demoproject.controller;

import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.entity.ProjectEntity;
import org.bill.demoproject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/queryProjectList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        List<ProjectEntity> hasProject = projectService.queryProjectList(projectEntity);
        if (hasProject.isEmpty()) {
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("fail");
        } else {
            httpResponseEntity.setCode("666");
            httpResponseEntity.setData(hasProject);
            System.out.println(hasProject);
            httpResponseEntity.setMessage("success");
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addProjectInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity1 = new HttpResponseEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        Date now = new Date();
        projectEntity.setCreationDate(now);
        projectEntity.setCreatedBy("admin");
        projectEntity.setLastUpdateDate(now);
        projectEntity.setLastUpdatedBy("admin");
//        System.out.println(projectEntity);
        try {
            int result = projectService.addProjectInfo(projectEntity);
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

    @RequestMapping(value = "/modifyProjectInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity2 = new HttpResponseEntity();
        Date now = new Date();
        projectEntity.setLastUpdateDate(now);
        projectEntity.setLastUpdatedBy("admin");
        try {
            int result = projectService.modifyProjectInfo(projectEntity);
//            System.out.println(result);
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

    @RequestMapping(value = "/deleteProjectById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity3 = new HttpResponseEntity();

        int result = projectService.deleteProjectById(projectEntity);
        System.out.println(result);
        if (result == 0) {
            httpResponseEntity3.setCode("0");
            httpResponseEntity3.setData(null);
            httpResponseEntity3.setMessage("fail");
        } else {
            httpResponseEntity3.setCode("666");
            httpResponseEntity3.setData(null);
            httpResponseEntity3.setMessage("success");
        }

        return httpResponseEntity3;
    }

}
