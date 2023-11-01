package org.bill.demoproject.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bill.demoproject.dao.UserEntityMapper;
import org.bill.demoproject.dao.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class UserServiceTest {

    @Test
    void selectUserInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("admin");
        UserService userService = new UserService(userEntityMapper);
        List<UserEntity> list = userService.selectUserInfo(userEntity);
        if (list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
            //log.info(">>qselectUserInfo用户登录测试成功");
        }
        sqlSession.close();

    }

    @Test
    void queryUserList() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testQuery");
        UserService userService = new UserService(userEntityMapper);
        List<UserEntity> list = userService.queryUserList(userEntity);
        if(list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        userEntity.setUsername("");
        list = userService.queryUserList(userEntity);
        if(list.isEmpty()) {
            // 记录error级别的信息
        } else {
            System.out.println(list);
            // 记录info级别的信息
        }
        sqlSession.close();
    }

    @Test
    void addUserInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testAdd");
        UserService userService = new UserService(userEntityMapper);
        int result = userService.addUserInfo(userEntity);
        if(result == 0) {
            // 记录error级别的信息
        } else {
            List<UserEntity> list = userService.queryUserList(userEntity);
            System.out.println(list);
        }
        sqlSession.close();
    }

    @Test
    void modifyUserInfo() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testModify");
        UserService userService = new UserService(userEntityMapper);
        int result = userService.modifyUserInfo(userEntity);
        if(result == 0) {
            // 记录error级别的信息
        } else {
            List<UserEntity> list = userService.queryUserList(userEntity);
            System.out.println(list);
            userEntity.setUsername("testModify1");
            result = userService.modifyUserInfo(userEntity);
            if(result == 0) {
                // 记录error级别的信息
            } else {
                List<UserEntity> list1 = userService.queryUserList(userEntity);
                System.out.println(list1);
            }
        }
        sqlSession.close();
    }

    @Test
    void deleteUserById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testDelete");
        UserService userService = new UserService(userEntityMapper);
        int result = userService.addUserInfo(userEntity);
        if(result == 0) {
            // 记录error级别的信息
        } else {
            List<UserEntity> list = userService.queryUserList(userEntity);
            System.out.println(list);
            int result1 = userService.deleteUserById(userEntity);
            if(result1 == 0) {
                // 记录error级别的信息
            } else {
                List<UserEntity> list1 = userService.queryUserList(userEntity);
                System.out.println(list1);
            }
        }
        sqlSession.close();
    }

    @Test
    void deleteUserByName() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testDeName");
        UserService userService = new UserService(userEntityMapper);
        int result = userService.addUserInfo(userEntity);
        if(result == 0) {
            // 记录error级别的信息
        } else {
            List<UserEntity> list = userService.queryUserList(userEntity);
            System.out.println(list);
            int result1 = userService.deleteUserByName(userEntity);
            if(result1 == 0) {
                // 记录error级别的信息
            } else {
                List<UserEntity> list1 = userService.queryUserList(userEntity);
                System.out.println(list1);
            }
        }
        sqlSession.close();
    }
}