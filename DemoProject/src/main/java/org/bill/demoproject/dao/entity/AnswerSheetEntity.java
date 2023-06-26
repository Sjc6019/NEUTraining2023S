package org.bill.demoproject.dao.entity;

import org.bill.demoproject.beans.AnswerEntity;

import java.util.List;

public class AnswerSheetEntity {
    private String id;
    private String answerUser;
    private String questionnaireId;
    private List<AnswerEntity> answer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public List<AnswerEntity> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AnswerEntity> answer) {
        this.answer = answer;
    }
}
