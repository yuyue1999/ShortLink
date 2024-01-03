package com.yy.shortlink.admin.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.admin.common.biz.user.UserContext;
import com.yy.shortlink.admin.dao.entity.GroupDO;
import com.yy.shortlink.admin.dao.mapper.GroupMapper;
import com.yy.shortlink.admin.dto.resp.ShortLinkGroupResDto;
import com.yy.shortlink.admin.service.GroupService;
import com.yy.shortlink.admin.utl.RandomGenerator;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        }while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .username(UserContext.getUsername())
                .name(groupName)
                .sortOrder(0)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupResDto> listGroup() {
        //TODO username
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, null)
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOS = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(groupDOS,ShortLinkGroupResDto.class);
    }

    private boolean hasGid(String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid,gid)
                .eq(GroupDO::getUsername,null);
        GroupDO hasGroup = baseMapper.selectOne(queryWrapper);
        return hasGroup == null;
    }


}
