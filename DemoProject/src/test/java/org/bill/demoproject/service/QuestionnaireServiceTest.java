package org.bill.demoproject.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.QuestionnaireMapper;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class QuestionnaireServiceTest {

    @Test
    void queryQuestionnaireInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionnaireMapper questionnaireMapper = sqlSession.getMapper(QuestionnaireMapper.class);
        QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireMapper);
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestionnaireName("");
        List<QuestionnaireEntity> list = questionnaireService.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(list.isEmpty());
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        sqlSession.close();
    }

    @Test
    void addQuestionnaireInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionnaireMapper questionnaireMapper = sqlSession.getMapper(QuestionnaireMapper.class);
        QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireMapper);
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        questionnaireEntity.setProjectId("4cd6ccb65c894eafaa70b12330f8c2f8");
        questionnaireEntity.setQuestionnaireName("test-service-add-1");
        questionnaireEntity.setQuestionnaireDescription("test-service-add-1");
        questionnaireEntity.setCreatedBy("admin");
        questionnaireEntity.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
        questionnaireEntity.setLastUpdatedBy("admin");
        questionnaireEntity.setLastUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));

        int result = questionnaireService.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(result);
        sqlSession.close();

    }

    @Test
    void modifyQuestionnaireInfo() {

    }

    @Test
    void deleteQuestionnaireById() {

    }
}