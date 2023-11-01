package org.bill.demoproject.controller;

import org.bill.demoproject.beans.HttpResponseEntity;
import org.bill.demoproject.dao.entity.UserEntity;
import org.bill.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * User Login
     * */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
            List<UserEntity> hasUser = userService.selectUserInfo(userEntity);
            if (CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(null);
                httpResponseEntity.setMessage("fail");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("success");
            }
        return httpResponseEntity;
    }

    /**
     * Query user list
     * */
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserList(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

            List<UserEntity> hasUser = userService.queryUserList(userEntity);
            if (CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("0"); // 0 means query fail
                httpResponseEntity.setData(null); // null means no data
                httpResponseEntity.setMessage("fail"); // fail means fail
            } else {
                httpResponseEntity.setCode("666"); // 666 means success
                httpResponseEntity.setData(hasUser); // hasUser means has user
                httpResponseEntity.setMessage("success"); // success means success
            }

        return httpResponseEntity;
    }

    /**
     * Add user
     * */
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.addUserInfo(userEntity);
            if (result !=0 ) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            }
        }catch (Exception e) {
            e.printStackTrace();
            httpResponseEntity.setCode("0");
            httpResponseEntity.setData(0);
            httpResponseEntity.setMessage("fail");
        }
        return httpResponseEntity;
    }

    /**
     * Modify user information
     * */
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            int result = userService.modifyUserInfo(userEntity);
            if (result != 0) {
                httpResponseEntity.setCode("10"); // 10 means modify success
                httpResponseEntity.setData(result); // result means result
                httpResponseEntity.setMessage("success"); // success means success
            }
        } catch (Exception e){
                e.printStackTrace();
                httpResponseEntity.setCode("0"); // 0 means modify fail
                httpResponseEntity.setData(0); // 0 means no data
                httpResponseEntity.setMessage("fail"); // fail means fail
            }

            return httpResponseEntity;
    }

    /**
     * Delete user by id
     * */
    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
            int result = userService.deleteUserById(userEntity);
            if (result !=0 ) {
                httpResponseEntity.setCode("10");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("success");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("fail");
            }

        return httpResponseEntity;
    }
}
