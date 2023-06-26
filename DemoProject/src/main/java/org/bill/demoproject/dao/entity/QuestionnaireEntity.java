package org.bill.demoproject.dao.entity;

import java.util.Date;

public class QuestionnaireEntity {
    private String id;
    private String projectId;
    private String questionnaireType;
    private String questionnaireName;
    private String questionnaireDescription;
    private String status;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdatedDate;

    private Date startTime;
    private Date endTime;
    private Date releaseTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQuestionnaireType() {
        return questionnaireType;
    }

    public void setQuestionnaireType(String questionnaireType) {
        this.questionnaireType = questionnaireType;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "QuestionnaireEntity{" +
                "\"id\":\"" + id + '\"' +
                ", \"projectId\":\"" + projectId + '\"' +
                ", \"questionnaireType\":\"" + questionnaireType + '\"' +
                ", \"questionnaireName\":\"" + questionnaireName + '\"' +
                ", \"questionnaireDescription\":\"" + questionnaireDescription + '\"' +
                ", \"status\":\"" + status + '\"' +
                ", \"createdBy\":\"" + createdBy + '\"' +
                ", \"creationDate\":\"" + creationDate + '\"' +
                ", \"lastUpdatedBy\":\"" + lastUpdatedBy + '\"' +
                ", \"lastUpdateDate\":\"" + lastUpdatedDate + '\"' +
                ", \"startTime\":\"" + startTime + '\"' +
                ", \"endTime\":\"" + endTime + '\"' +
                ", \"releaseTime\":\"" + releaseTime + '\"' +
                '}';
    }
}
