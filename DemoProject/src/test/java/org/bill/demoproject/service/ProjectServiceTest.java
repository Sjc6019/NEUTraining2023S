package org.bill.demoproject.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.ProjectEntityMapper;
import org.bill.demoproject.dao.UserEntityMapper;
import org.bill.demoproject.dao.entity.ProjectEntity;
import org.bill.demoproject.dao.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjectServiceTest {

    @Test
    void queryProjectList() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("test");
        ProjectService projectService = new ProjectService(projectEntityMapper);
        List<ProjectEntity> list = projectService.queryProjectList(projectEntity);
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        projectEntity.setProjectName("");
        list = projectService.queryProjectList(projectEntity);
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        sqlSession.close();
    }

    @Test
    void addProjectInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity = new ProjectEntity();
        Date now = new Date();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId(UUIDUtil.getOneUUID());
        projectEntity.setProjectName("test");
        projectEntity.setProjectContent("test-content");
        projectEntity.setCreationDate(now);
        projectEntity.setCreatedBy("admin");
        projectEntity.setLastUpdateDate(now);
        projectEntity.setLastUpdatedBy("admin");
        ProjectService projectService = new ProjectService(projectEntityMapper);
        int result = projectService.addProjectInfo(projectEntity);
        if (result == 0) {
            // 记录error级别的信息
        } else {
            List<ProjectEntity> list = projectService.queryProjectList(projectEntity);
            System.out.println(list);
        }
        sqlSession.close();
    }

    @Test
    void modifyProjectInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId(UUIDUtil.getOneUUID());
        projectEntity.setProjectName("test");
        projectEntity.setProjectContent("test-content");
        ProjectService projectService = new ProjectService(projectEntityMapper);
        int result = projectService.addProjectInfo(projectEntity);
        if (result == 0) {
            // 记录error级别的信息
        } else {
            List<ProjectEntity> list = projectService.queryProjectList(projectEntity);
            System.out.println(list);
            projectEntity.setProjectName("test1");
            projectEntity.setProjectContent("test-content1");
            int result1 = projectService.modifyProjectInfo(projectEntity);
            if (result1 == 0) {
                // 记录error级别的信息
            } else {
                List<ProjectEntity> list1 = projectService.queryProjectList(projectEntity);
                System.out.println(list1);
            }
        }
        sqlSession.close();
    }

    @Test
    void deleteProjectById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId(UUIDUtil.getOneUUID());
        projectEntity.setProjectName("test");
        projectEntity.setProjectContent("test-content");
        ProjectService projectService = new ProjectService(projectEntityMapper);
        int result = projectService.addProjectInfo(projectEntity);
        if (result == 0) {
            // 记录error级别的信息
        } else {
            List<ProjectEntity> list = projectService.queryProjectList(projectEntity);
            System.out.println(list);
            int result1 = projectService.deleteProjectById(projectEntity);
            if (result1 == 0) {
                // 记录error级别的信息
            } else {
                List<ProjectEntity> list1 = projectService.queryProjectList(projectEntity);
                System.out.println(list1);
            }
        }
        sqlSession.close();
    }
}