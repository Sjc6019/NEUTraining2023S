package org.bill.demoproject.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bill.demoproject.beans.OptionEntity;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.ProblemEntityMapper;
import org.bill.demoproject.dao.entity.ProblemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Handler;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProblemServiceTest {

    @Test
    void queryProblem() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProblemEntityMapper problemEntityMapper = sqlSession.getMapper(ProblemEntityMapper.class);
        ProblemService problemService = new ProblemService(problemEntityMapper);
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setProblemName("");
        List<ProblemEntity> list = problemService.queryProblem(problemEntity);
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
    void addProblem() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProblemEntityMapper problemEntityMapper = sqlSession.getMapper(ProblemEntityMapper.class);
        ProblemService problemService = new ProblemService(problemEntityMapper);
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(UUIDUtil.getOneUUID());
        problemEntity.setQuestionnaireId("4cd6ccb65c894eafaa70b12330f8c2f8");
        problemEntity.setProblemName("test-service-add-1");
        problemEntity.setProblemType(1);
        OptionEntity optionEntity1 = new OptionEntity();
        optionEntity1.setChooseTerm("test1");
        OptionEntity optionEntity2 = new OptionEntity();
        optionEntity2.setChooseTerm("test2");
        List<OptionEntity> optionEntityList = List.of(optionEntity1, optionEntity2);
        problemEntity.setProblemOptions(optionEntityList);
        List<ProblemEntity> problemEntityList = List.of(problemEntity);
        problemService.addProblem(problemEntityList);
        List<ProblemEntity> list = problemService.queryProblem(problemEntity);
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
    void modifyProblem() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProblemEntityMapper problemEntityMapper = sqlSession.getMapper(ProblemEntityMapper.class);
        ProblemService problemService = new ProblemService(problemEntityMapper);
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(UUIDUtil.getOneUUID());
        problemEntity.setQuestionnaireId("4cd6ccb65c894eafaa70b12330f8c2f8");
        problemEntity.setProblemName("test-service-add-2");
        problemEntity.setProblemType(1);
        OptionEntity optionEntity1 = new OptionEntity();
        optionEntity1.setChooseTerm("test1");
        OptionEntity optionEntity2 = new OptionEntity();
        optionEntity2.setChooseTerm("test2");
        List<OptionEntity> optionEntityList = List.of(optionEntity1, optionEntity2);
        problemEntity.setProblemOptions(optionEntityList);
        List<ProblemEntity> problemEntityList = List.of(problemEntity);
        problemService.addProblem(problemEntityList);
        List<ProblemEntity> list = problemService.queryProblem(problemEntity);
        System.out.println(list.isEmpty());
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        problemEntity.setProblemName("test-service-modify-1");
        problemService.modifyProblem(problemEntity);
        list = problemService.queryProblem(problemEntity);
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
    void deleteProblemById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProblemEntityMapper problemEntityMapper = sqlSession.getMapper(ProblemEntityMapper.class);
        ProblemService problemService = new ProblemService(problemEntityMapper);
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(UUIDUtil.getOneUUID());
        problemEntity.setQuestionnaireId("4cd6ccb65c894eafaa70b12330f8c2f8");
        problemEntity.setProblemName("test-service-add-3");
        problemEntity.setProblemType(1);
        OptionEntity optionEntity1 = new OptionEntity();
        optionEntity1.setChooseTerm("test1");
        OptionEntity optionEntity2 = new OptionEntity();
        optionEntity2.setChooseTerm("test2");
        List<OptionEntity> optionEntityList = List.of(optionEntity1, optionEntity2);
        problemEntity.setProblemOptions(optionEntityList);
        List<ProblemEntity> problemEntityList = List.of(problemEntity);
        problemService.addProblem(problemEntityList);
        List<ProblemEntity> list = problemService.queryProblem(problemEntity);
        System.out.println(list.isEmpty());
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        problemService.deleteProblemById(problemEntity);
        list = problemService.queryProblem(problemEntity);
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
    void deleteProblemByQuestionnaireId() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProblemEntityMapper problemEntityMapper = sqlSession.getMapper(ProblemEntityMapper.class);
        ProblemService problemService = new ProblemService(problemEntityMapper);
        ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId(UUIDUtil.getOneUUID());
        problemEntity.setQuestionnaireId("4cd6ccb65c894eafaa70b12330f8c2f8");
        problemEntity.setProblemName("test-service-delete");
        problemEntity.setProblemType(1);
        OptionEntity optionEntity1 = new OptionEntity();
        optionEntity1.setChooseTerm("test1");
        OptionEntity optionEntity2 = new OptionEntity();
        optionEntity2.setChooseTerm("test2");
        List<OptionEntity> optionEntityList = List.of(optionEntity1, optionEntity2);
        problemEntity.setProblemOptions(optionEntityList);
        List<ProblemEntity> problemEntityList = List.of(problemEntity);
        problemService.addProblem(problemEntityList);
        List<ProblemEntity> list = problemService.queryProblem(problemEntity);
        System.out.println(list.isEmpty());
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        problemService.deleteProblemByQuestionnaireId(problemEntity.getQuestionnaireId());
        list = problemService.queryProblem(problemEntity);
        System.out.println(list.isEmpty());
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        sqlSession.close();

    }
}