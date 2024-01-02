package com.yy.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.yy.shortlink.admin.dao.entity.UserDO;
import com.yy.shortlink.admin.dao.mapper.UserMapper;
import com.yy.shortlink.admin.dto.resp.UserRespDto;
import com.yy.shortlink.admin.service.UserService;
import com.yy.shortlink.admin.common.convention.exception.ClientException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserRespDto getUserbyUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername,username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException(BaseErrorCode.USER_NAME_VERIFY_ERROR);
        }
        UserRespDto result = new UserRespDto();
        BeanUtils.copyProperties(userDO,result);
        return result;
    }
}
