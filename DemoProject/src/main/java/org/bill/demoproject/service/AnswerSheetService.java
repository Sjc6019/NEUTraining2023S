package org.bill.demoproject.service;

import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.AnswerSheetEntityMapper;
import org.bill.demoproject.dao.entity.AnswerSheetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerSheetService {
    @Autowired
    private AnswerSheetEntityMapper answerSheetEntityMapper;

    public AnswerSheetService(AnswerSheetEntityMapper answerSheetEntityMapper) {
        this.answerSheetEntityMapper = answerSheetEntityMapper;
    }

    public List<AnswerSheetEntity> queryAnswerSheet(AnswerSheetEntity answerSheetEntity) {
        List<AnswerSheetEntity> result = answerSheetEntityMapper.queryAnswerSheet(answerSheetEntity);
        return result;
    }

    public int addAnswerSheet(AnswerSheetEntity answerSheetEntity) {
        answerSheetEntity.setId(UUIDUtil.getOneUUID());
        int result = answerSheetEntityMapper.addAnswerSheet(answerSheetEntity);
        return result;
    }

    public int deleteAnswerSheetById(AnswerSheetEntity answerSheetEntity) {
        int result = answerSheetEntityMapper.deleteAnswerSheetById(answerSheetEntity);
        return result;
    }
}
