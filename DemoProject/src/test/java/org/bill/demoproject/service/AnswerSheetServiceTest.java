package org.bill.demoproject.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bill.demoproject.beans.AnswerEntity;
import org.bill.demoproject.dao.AnswerSheetEntityMapper;
import org.bill.demoproject.dao.entity.AnswerSheetEntity;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerSheetServiceTest {

    @Test
    void queryAnswerSheet() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AnswerSheetEntityMapper answerSheetEntityMapper = sqlSession.getMapper(AnswerSheetEntityMapper.class);
        AnswerSheetService answerSheetService = new AnswerSheetService(answerSheetEntityMapper);
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        answerSheetEntity.setAnswerUser("test");
        List<AnswerSheetEntity> list = answerSheetService.queryAnswerSheet(answerSheetEntity);
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        answerSheetEntity.setAnswerUser("");
        list = answerSheetService.queryAnswerSheet(answerSheetEntity);
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        sqlSession.close();

    }

    @Test
    void addAnswerSheet() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AnswerSheetEntityMapper answerSheetEntityMapper = sqlSession.getMapper(AnswerSheetEntityMapper.class);
        AnswerSheetService answerSheetService = new AnswerSheetService(answerSheetEntityMapper);
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        List<AnswerEntity> answerEntityList = new ArrayList<>();
        Date now = new Date();
        answerSheetEntity.setAnswerUser("test");
        answerSheetEntity.setAnswerTime(now);
        answerSheetEntity.setQuestionnaireId("testQuestionnaireID");
        answerSheetEntity.setAnswer(answerEntityList);
        int result = answerSheetService.addAnswerSheet(answerSheetEntity);
        if (result == 0) {
            // 记录error级别的信息
        } else {
            // 记录info级别的信息
            List<AnswerSheetEntity> list = answerSheetService.queryAnswerSheet(answerSheetEntity);
            System.out.println(list);
        }
        sqlSession.close();
    }

    @Test
    void deleteAnswerSheetById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AnswerSheetEntityMapper answerSheetEntityMapper = sqlSession.getMapper(AnswerSheetEntityMapper.class);
        AnswerSheetService answerSheetService = new AnswerSheetService(answerSheetEntityMapper);
        AnswerSheetEntity answerSheetEntity = new AnswerSheetEntity();
        List<AnswerEntity> answerEntityList = new ArrayList<>();
        Date now = new Date();
        answerSheetEntity.setAnswerUser("testDelete");
        answerSheetEntity.setAnswerTime(now);
        answerSheetEntity.setQuestionnaireId("testQuestionnaireID");
        answerSheetEntity.setAnswer(answerEntityList);
        int result = answerSheetService.addAnswerSheet(answerSheetEntity);
        if (result == 0) {
            // 记录error级别的信息
        } else {
            // 记录info级别的信息
            List<AnswerSheetEntity> list = answerSheetService.queryAnswerSheet(answerSheetEntity);
            System.out.println(list);
            int deleteResult = answerSheetService.deleteAnswerSheetById(answerSheetEntity);
            if (deleteResult == 0) {
                // 记录error级别的信息
            } else {
                // 记录info级别的信息
                list = answerSheetService.queryAnswerSheet(answerSheetEntity);
                System.out.println(list);
            }
        }
        sqlSession.close();

    }
}