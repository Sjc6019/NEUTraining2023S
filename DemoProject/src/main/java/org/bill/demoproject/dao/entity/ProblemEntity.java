package org.bill.demoproject.dao.entity;

import org.bill.demoproject.beans.OptionEntity;

import java.util.Arrays;

public class ProblemEntity {
    private String id;
    private String questionnaireId;
    private String problemType;
    private String problemName;
    private boolean mustAnswer;
    private OptionEntity[] option;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public boolean isMustAnswer() {
        return mustAnswer;
    }

    public void setMustAnswer(boolean mustAnswer) {
        this.mustAnswer = mustAnswer;
    }

    public OptionEntity[] getOption() {
        return option;
    }

    public void setOption(OptionEntity[] option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "ProblemEntity{" +
                "\"id\":\"" + id + '\"' +
                ", \"questionnaireId\":\"" + questionnaireId + '\"' +
                ", \"problemType\":\"" + problemType + '\"' +
                ", \"problemName\":\"" + problemName + '\"' +
                ", \"mustAnswer\":" + mustAnswer +
                ", \"option\":" + Arrays.toString(option) +
                '}';
    }
}
