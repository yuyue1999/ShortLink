package com.yy.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.dao.entity.UserDO;
import com.yy.shortlink.admin.dto.req.UserLoginReqDto;
import com.yy.shortlink.admin.dto.req.UserRegisterReqDto;
import com.yy.shortlink.admin.dto.req.UserUpdateReqDto;
import com.yy.shortlink.admin.dto.resp.UserInfoDTO;
import com.yy.shortlink.admin.dto.resp.UserRespDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;

public interface UserService extends IService<UserDO> {

    UserRespDto getUserbyUsername(String username);

    Boolean hasUsername(String username);

    void UserRegister(UserRegisterReqDto userInfo);

    void update(UserUpdateReqDto requestParam);

    UserInfoDTO login(UserLoginReqDto requestParam);

    Boolean checkLogin(String username,String token);

    void logout(String username,String token);

}
