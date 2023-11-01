package org.bill.demoproject.service;

import org.bill.demoproject.common.utils.UUIDUtil;
import org.bill.demoproject.dao.UserEntityMapper;
import org.bill.demoproject.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;

    public UserService(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    /**
     * Find user for login
     */
    public List<UserEntity> selectUserInfo(UserEntity userEntity) {
        List<UserEntity> result = userEntityMapper.selectUserInfo(userEntity);
        return result;
    }

    /**
     * Query user list
     */
    public List<UserEntity> queryUserList(UserEntity userEntity) {
        List<UserEntity> result = userEntityMapper.queryUserList(userEntity);
        return result;
    }

    /**
     * Create user
     */
    public int addUserInfo(UserEntity userEntity) {
        userEntity.setId(UUIDUtil.getOneUUID());
        userEntity.setStatus("1");
        int userResult = userEntityMapper.insert(userEntity);
        return userResult;
    }

    /**
     * Modify user information
     */
    public int modifyUserInfo(UserEntity userEntity) {
        int userResult = userEntityMapper.updateByPrimaryKeySelective(userEntity);
        return userResult;
    }

    /**
     * Delete user by id
     */
    public int deleteUserById(UserEntity userEntity) {
        int userResult = userEntityMapper.deleteUserById(userEntity);
        return userResult;
    }

    /**
     * Delete user by name
     */
    public int deleteUserByName(UserEntity userEntity) {
        int userResult = userEntityMapper.deleteUserByName(userEntity);
        return userResult;
    }

}
