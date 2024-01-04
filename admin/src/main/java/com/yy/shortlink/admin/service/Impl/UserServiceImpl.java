package com.yy.shortlink.admin.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.yy.shortlink.admin.dao.entity.UserDO;
import com.yy.shortlink.admin.dao.mapper.UserMapper;
import com.yy.shortlink.admin.dto.req.UserLoginReqDto;
import com.yy.shortlink.admin.dto.req.UserRegisterReqDto;
import com.yy.shortlink.admin.dto.req.UserUpdateReqDto;

import com.yy.shortlink.admin.dto.resp.UserLoginRespDto;
import com.yy.shortlink.admin.dto.resp.UserRespDto;
import com.yy.shortlink.admin.service.UserService;
import com.yy.shortlink.admin.common.convention.exception.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.yy.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    private final RedissonClient redissonClient;

    private final StringRedisTemplate stringRedisTemplate;
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

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void UserRegister(UserRegisterReqDto requestParam) {
        if (hasUsername(requestParam.getUsername())){
            throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try{
            if (lock.tryLock()){
                try{
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam,UserDO.class));
                if (inserted < 1) {
                    throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
                }
                }catch (DuplicateKeyException e){
                    throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
                }

                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
            }else {
                throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
            }
        }catch (Exception e){
            throw new ClientException(BaseErrorCode.USER_NAME_EXIST_ERROR);
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void update(UserUpdateReqDto requestParam) {
        // TODO is login or not
        // TODO: Redis and divided tables
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class).eq(UserDO::getUsername,requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam,UserDO.class),updateWrapper);
    }

    @Override
    public UserLoginRespDto login(UserLoginReqDto requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername,requestParam.getUsername())
                .eq(UserDO::getPassword,requestParam.getPassword())
                .eq(UserDO::getDelFlag,0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null){
            throw new ClientException(BaseErrorCode.USER_LOGIN_FAILURE);
        }

        Boolean hasLogin = stringRedisTemplate.hasKey("login_" + requestParam.getUsername());
        if(hasLogin!=null && hasLogin){
            throw new ClientException(BaseErrorCode.USER_HAS_LOGIN);
        }


        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(),uuid,JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L,TimeUnit.MINUTES);
        return new UserLoginRespDto(uuid);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get("login_"+username,token)!=null;
    }

    @Override
    public void logout(String username, String token) {
        if (!checkLogin(username,token)){
            throw new ClientException(BaseErrorCode.USER_NOT_LOGIN);
        }
        stringRedisTemplate.delete("login_"+username);
    }
}
