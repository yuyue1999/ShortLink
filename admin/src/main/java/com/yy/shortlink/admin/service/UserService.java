package com.yy.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.admin.dao.entity.UserDO;
import com.yy.shortlink.admin.dto.req.UserRegisterReqDto;
import com.yy.shortlink.admin.dto.resp.UserRespDto;

import java.lang.reflect.InvocationTargetException;

public interface UserService extends IService<UserDO> {

    UserRespDto getUserbyUsername(String username);

    Boolean hasUsername(String username);

    void UserRegister(UserRegisterReqDto userInfo);

}
