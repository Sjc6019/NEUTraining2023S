package org.bill.demoproject.service;

import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.QuestionnaireMapper;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    public QuestionnaireService(QuestionnaireMapper questionnaireMapper) {
        this.questionnaireMapper = questionnaireMapper;
    }

    public List<QuestionnaireEntity> queryQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        List<QuestionnaireEntity> result = questionnaireMapper.queryQuestionnaireList(questionnaireEntity);
        return result;
    }

    public int addQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        int result = questionnaireMapper.addQuestionnaireInfo(questionnaireEntity);
        return result;
    }

    public int modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity) {
        int result = questionnaireMapper.updateByPrimaryKeySelective(questionnaireEntity);
        return result;
    }

    public int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity) {
        int result = questionnaireMapper.deleteQuestionnaireById(questionnaireEntity);
        return result;
    }

}
