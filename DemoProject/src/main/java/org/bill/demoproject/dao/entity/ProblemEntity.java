package org.bill.demoproject.dao.entity;

import org.bill.demoproject.beans.OptionEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ProblemEntity implements Serializable {
    private String id;
    private String questionnaireId;
    private Integer problemType;
    private String problemName;
    private boolean mustAnswer;
    private List<OptionEntity> problemOptions;
    private String leftTitle;

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

    public Integer getProblemType() {
        return problemType;
    }

    public void setProblemType(Integer problemType) {
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

    public List<OptionEntity> getProblemOptions() {
        return problemOptions;
    }

    public void setProblemOptions(List<OptionEntity> problemOptions) {
        this.problemOptions = problemOptions;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    @Override
    public String toString() {
        if (problemOptions == null) {
            return "ProblemEntity{" +
                    "\"id\":\"" + id + '\"' +
                    ", \"questionnaireId\":\"" + questionnaireId + '\"' +
                    ", \"problemType\":\"" + problemType + '\"' +
                    ", \"problemName\":\"" + problemName + '\"' +
                    ", \"mustAnswer\":" + mustAnswer +
                    ", \"problemOptions\":null" +
                    ", \"leftTitle\":\"" + leftTitle + '\"' +
                    '}';
        }else {
            return "ProblemEntity{" +
                    "\"id\":\"" + id + '\"' +
                    ", \"questionnaireId\":\"" + questionnaireId + '\"' +
                    ", \"problemType\":\"" + problemType + '\"' +
                    ", \"problemName\":\"" + problemName + '\"' +
                    ", \"mustAnswer\":" + mustAnswer +
                    ", \"problemOptions\":" + Arrays.toString(problemOptions.toArray()) +
                    ", \"leftTitle\":\"" + leftTitle + '\"' +
                    '}';
        }
    }
}
