package org.bill.demoproject.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.QuestionnaireEntityMapper;
import org.bill.demoproject.dao.entity.QuestionnaireEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@SpringBootTest
class QuestionnaireServiceTest {

    @Test
    void queryQuestionnaireInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireEntityMapper);
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
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireEntityMapper);
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        questionnaireEntity.setProjectId("4cd6ccb65c894eafaa70b12330f8c2f8");
        questionnaireEntity.setQuestionnaireName("test-service-add-1");
        questionnaireEntity.setQuestionnaireDescription("test-service-add-1");
        questionnaireEntity.setCreatedBy("admin");
        questionnaireEntity.setStatus("1");
        questionnaireEntity.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
        questionnaireEntity.setLastUpdatedBy("admin");
        questionnaireEntity.setLastUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));

        int result = questionnaireService.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(result);
        sqlSession.close();

    }

    @Test
    void modifyQuestionnaireInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireEntityMapper);
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        questionnaireEntity.setProjectId("4cd6ccb65c894eafaa70b12330f8c2f8");
        questionnaireEntity.setQuestionnaireName("test-service-add-temp-1");
        questionnaireEntity.setQuestionnaireDescription("test-service-add-temp-1");
        questionnaireEntity.setCreatedBy("admin");
        questionnaireEntity.setStatus("1");
        questionnaireEntity.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
        questionnaireEntity.setLastUpdatedBy("admin");
        questionnaireEntity.setLastUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));
        int result = questionnaireService.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(result);
        questionnaireEntity.setQuestionnaireName("");
        List<QuestionnaireEntity> list1 = questionnaireService.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(list1);
        questionnaireEntity.setQuestionnaireName("test-service-modify-1");
        questionnaireEntity.setQuestionnaireDescription("test-service-modify-1");
        int result2 = questionnaireService.modifyQuestionnaireInfo(questionnaireEntity);
        System.out.println(result2);
        questionnaireEntity.setQuestionnaireName("");
        List<QuestionnaireEntity> list2 = questionnaireService.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(list2);
        sqlSession.close();
    }

    @Test
    void deleteQuestionnaireById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QuestionnaireEntityMapper questionnaireEntityMapper = sqlSession.getMapper(QuestionnaireEntityMapper.class);
        QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireEntityMapper);
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        questionnaireEntity.setProjectId("4cd6ccb65c894eafaa70b12330f8c2f8");
        questionnaireEntity.setQuestionnaireName("test-service-add-temp-2");
        questionnaireEntity.setQuestionnaireDescription("test-service-add-temp-2");
        questionnaireEntity.setCreatedBy("admin");
        questionnaireEntity.setStatus("1");
        questionnaireEntity.setCreationDate(new java.sql.Date(new java.util.Date().getTime()));
        questionnaireEntity.setLastUpdatedBy("admin");
        questionnaireEntity.setLastUpdatedDate(new java.sql.Date(new java.util.Date().getTime()));
        int result = questionnaireService.addQuestionnaireInfo(questionnaireEntity);
        System.out.println(result);
        questionnaireEntity.setQuestionnaireName("");
        List<QuestionnaireEntity> list1 = questionnaireService.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(list1);
        int result2 = questionnaireService.deleteQuestionnaireById(questionnaireEntity);
        System.out.println(result2);
        List<QuestionnaireEntity> list2 = questionnaireService.queryQuestionnaireInfo(questionnaireEntity);
        System.out.println(list2);
        sqlSession.close();
    }
}