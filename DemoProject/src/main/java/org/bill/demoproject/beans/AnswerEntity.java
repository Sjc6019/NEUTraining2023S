package org.bill.demoproject.beans;

public class AnswerEntity {
    private String problemId;
    private String answer;

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "problemId='" + problemId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
