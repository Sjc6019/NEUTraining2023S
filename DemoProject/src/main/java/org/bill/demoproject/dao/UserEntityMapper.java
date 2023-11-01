package org.bill.demoproject.dao;

import jdk.jfr.Registered;
import org.apache.ibatis.annotations.Mapper;
import org.bill.demoproject.dao.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//@Repository
@Component
@Mapper
public interface UserEntityMapper {
    /**
     * Query user list
     * */
    List<UserEntity> queryUserList(UserEntity userEntity);

    /**
     * Create user base information
     * */
    int insert(UserEntity userEntity);

    /**
     * Delete user by id
     * */
    int deleteUserById(UserEntity userEntity);
    /**
     * Delete user by name
     * */
    int deleteUserByName(UserEntity userEntity);

    /**
     * edit user information
     * */
    int updateByPrimaryKeySelective(UserEntity userEntity);

    /**
     * Find user
     * */
    List<UserEntity> selectUserInfo(UserEntity userEntity);
}
