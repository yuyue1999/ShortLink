package com.yy.shortlink.project.service.Impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import com.yy.shortlink.project.dao.mapper.ShortLinkMapper;
import com.yy.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.yy.shortlink.project.service.RecycleBinService;
import org.springframework.stereotype.Service;

@Service
public class RecycleBinServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDo> implements RecycleBinService {
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
    }
}
