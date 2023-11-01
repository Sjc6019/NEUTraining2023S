package org.bill.demoproject;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.UserEntityMapper;
import org.bill.demoproject.dao.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;

@SpringBootTest
class DemoProjectApplicationTests {

//    //    @Test
////    void contextLoads() {
////    }
//    Logger log = LogManager.getLogger(DemoProjectApplicationTests.class);
//
//    @Test
//    public void queryUserList() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
//        if(CollectionUtils.isEmpty(list)){
//            // 记录error级别的信息
//        }else{
//            System.out.println(list);
//            // 记录info级别的信息
//            log.info(">>queryUserList用户列表查询测试成功");
//        }
//    }
//
//    @Test
//    public void selectUserInfo() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("admin");
//        userEntity.setPassword("admin");
//        List<UserEntity> list = userEntityMapper.selectUserInfo(userEntity);
//        if (CollectionUtils.isEmpty(list)) {
//            // 记录error级别的信息
//        } else {
//            System.out.println(list);
//            // 记录info级别的信息
//            log.info(">>qselectUserInfo用户登录测试成功");
//        }
//    }
//
//    @Test
//    public void insert() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setId(UUIDUtil.getOneUUID());
//        userEntity.setStatus("1");
//        userEntity.setUsername("LS");
//        userEntity.setPassword("123");
//        int i = userEntityMapper.insert(userEntity);
//        if (i == 0) {
//            // 记录error级别的信息
//        } else {
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>insert用户插入测试成功");
//        }
//    }
//
//    @Test
//    public void deleteUserByName() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建UserMapper对象，mybatis自动生成mapper代理对象
//        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
//        //调用userMapper的方法
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername("aaaaa");
//        int i = userEntityMapper.deleteUserByName(userEntity);
//        if(i==0){
//            // 记录error级别的信息
//        }else{
//            System.out.println(i);
//            // 记录info级别的信息
//            log.info(">>delete用户删除测试成功");
//        }
//    }


}


