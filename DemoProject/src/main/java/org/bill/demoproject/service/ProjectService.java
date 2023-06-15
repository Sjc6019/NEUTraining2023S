package org.bill.demoproject.service;

import org.bill.demoproject.dao.ProjectEntityMapper;
import org.bill.demoproject.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    public ProjectService(ProjectEntityMapper projectEntityMapper) {
        this.projectEntityMapper = projectEntityMapper;
    }

    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity) {
        List<ProjectEntity> result = projectEntityMapper.queryProjectList(projectEntity);
        return result;
    }

    public int addProjectInfo(ProjectEntity projectEntity) {
        int result = projectEntityMapper.addProjectInfo(projectEntity);
        return result;
    }

    public int modifyProjectInfo(ProjectEntity projectEntity) {
        int result = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        return result;
    }

    public int deleteProjectById(ProjectEntity projectEntity) {
        int result = projectEntityMapper.deleteProjectById(projectEntity);
        return result;
    }
}
