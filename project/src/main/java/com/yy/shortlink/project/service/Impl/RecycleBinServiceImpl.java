package com.yy.shortlink.project.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.project.common.constant.RedisKeyConstant;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import com.yy.shortlink.project.dao.mapper.ShortLinkMapper;
import com.yy.shortlink.project.dto.req.*;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.yy.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecycleBinServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDo> implements RecycleBinService {

    private final StringRedisTemplate stringRedisTemplate;
    @Override
    public void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        LambdaUpdateWrapper<ShortLinkDo> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDo.class)
                .eq(ShortLinkDo::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDo::getGid, requestParam.getGid())
                .eq(ShortLinkDo::getEnableStatus, 0)
                .eq(ShortLinkDo::getDelFlag, 0);
        ShortLinkDo shortLinkDo = ShortLinkDo.builder()
                .enableStatus(1)
                .build();
        baseMapper.update(shortLinkDo,updateWrapper);
        stringRedisTemplate.delete(String.format(RedisKeyConstant.GOTO_SHORT_LINK_KEY,requestParam.getFullShortUrl()));
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecyclePageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDo> queryWrapper = Wrappers.lambdaQuery(ShortLinkDo.class)
                .in(ShortLinkDo::getGid, requestParam.getGidList())
                .eq(ShortLinkDo::getEnableStatus, 1)
                .eq(ShortLinkDo::getDelFlag, 0);
        IPage<ShortLinkDo> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam) {

        LambdaUpdateWrapper<ShortLinkDo> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDo.class)
                .eq(ShortLinkDo::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDo::getGid, requestParam.getGid())
                .eq(ShortLinkDo::getEnableStatus, 1)
                .eq(ShortLinkDo::getDelFlag, 0);
        ShortLinkDo shortLinkDo = ShortLinkDo.builder()
                .enableStatus(0)
                .build();
        baseMapper.update(shortLinkDo,updateWrapper);
        stringRedisTemplate.delete(String.format(RedisKeyConstant.GOTO_IS_NULL_SHORT_LINK_KEY,requestParam.getFullShortUrl()));


    }

    @Override
    public void deleteRecycleBin(RecycleBinDeleteReqDTO requestParam) {
        LambdaUpdateWrapper<ShortLinkDo> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDo.class)
                .eq(ShortLinkDo::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDo::getGid, requestParam.getGid())
                .eq(ShortLinkDo::getEnableStatus, 1)
                .eq(ShortLinkDo::getDelFlag, 0);
        baseMapper.delete(updateWrapper);
    }
}
