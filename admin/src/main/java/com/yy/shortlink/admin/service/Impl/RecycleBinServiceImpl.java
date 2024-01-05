package com.yy.shortlink.admin.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yy.shortlink.admin.common.biz.user.UserContext;
import com.yy.shortlink.admin.common.convention.exception.ServiceException;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.dao.entity.GroupDO;
import com.yy.shortlink.admin.dao.mapper.GroupMapper;
import com.yy.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkRecyclePageReqDTO;
import com.yy.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.yy.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };
    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecyclePageReqDTO requestParam) {

        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOS = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOS)){
            throw new ServiceException("no gropus");
        }
        requestParam.setGidList(groupDOS.stream().map(GroupDO::getGid).toList());


        return shortLinkRemoteService.pageRecycleBinShortLink(requestParam);
    }
}
