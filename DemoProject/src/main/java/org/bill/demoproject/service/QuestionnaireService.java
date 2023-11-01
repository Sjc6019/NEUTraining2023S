package org.bill.demoproject.service;

import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.QuestionnaireEntityMapper;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    public QuestionnaireService(QuestionnaireEntityMapper questionnaireEntityMapper) {
        this.questionnaireEntityMapper = questionnaireEntityMapper;
    }

    public List<QuestionnaireEntity> queryQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        List<QuestionnaireEntity> result = questionnaireEntityMapper.queryQuestionnaireList(questionnaireEntity);
        return result;
    }

    public int addQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        int result = questionnaireEntityMapper.addQuestionnaireInfo(questionnaireEntity);
        return result;
    }

    public int modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        int result = questionnaireEntityMapper.updateByPrimaryKeySelective(questionnaireEntity);
        return result;
    }

    public int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity) {
        int result = questionnaireEntityMapper.deleteQuestionnaireById(questionnaireEntity);
        return result;
    }

}
