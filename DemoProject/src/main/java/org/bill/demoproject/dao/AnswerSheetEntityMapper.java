package org.bill.demoproject.dao;

import org.apache.ibatis.annotations.Mapper;
import org.bill.demoproject.dao.entity.AnswerSheetEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerSheetEntityMapper {
    List<AnswerSheetEntity> queryAnswerSheet(AnswerSheetEntity answerSheetEntity);
    int addAnswerSheet(AnswerSheetEntity answerSheetEntity);
    int deleteAnswerSheetById(AnswerSheetEntity answerSheetEntity);
}
