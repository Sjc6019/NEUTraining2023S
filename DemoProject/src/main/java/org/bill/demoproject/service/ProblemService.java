package org.bill.demoproject.service;

import org.bill.demoproject.dao.ProblemEntityMapper;
import org.bill.demoproject.dao.entity.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {
    @Autowired
    private ProblemEntityMapper problemEntityMapper;

    public ProblemService(ProblemEntityMapper problemEntityMapper) {
        this.problemEntityMapper = problemEntityMapper;
    }

    public List<ProblemEntity> queryProblem(ProblemEntity problemEntity) {
        List<ProblemEntity> result = problemEntityMapper.queryProblem(problemEntity);
        return result;
    }

    public int addProblem(List<ProblemEntity> problemEntity) {
        int result = problemEntityMapper.addProblem(problemEntity);
        return result;
    }

    public int modifyProblem(ProblemEntity problemEntity) {
        int result = problemEntityMapper.updateByPrimaryKeySelective(problemEntity);
        return result;
    }

    public int deleteProblemById(ProblemEntity problemEntity) {
        int result = problemEntityMapper.deleteProblemById(problemEntity);
        return result;
    }

    public int deleteProblemByQuestionnaireId(String questionnaireId) {
        int result = problemEntityMapper.deleteProblemByQuestionnaireId(questionnaireId);
        return result;
    }
}
