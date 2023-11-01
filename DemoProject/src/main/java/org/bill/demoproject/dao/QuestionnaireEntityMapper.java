package org.bill.demoproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionnaireEntityMapper {
    List<QuestionnaireEntity> queryQuestionnaireList(QuestionnaireEntity questionnaireEntity);
    int addQuestionnaireInfo(QuestionnaireEntity questionnaireEntity);
    int updateByPrimaryKeySelective(QuestionnaireEntity questionnaireEntity);
    int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity);
}
