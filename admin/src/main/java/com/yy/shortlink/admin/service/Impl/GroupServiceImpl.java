package com.yy.shortlink.admin.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.admin.common.biz.user.UserContext;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.dao.entity.GroupDO;
import com.yy.shortlink.admin.dao.mapper.GroupMapper;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupSortDto;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDto;
import com.yy.shortlink.admin.dto.resp.ShortLinkGroupResDto;
import com.yy.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.yy.shortlink.admin.remote.dto.resp.ShortLinkCountQueryRespDTO;
import com.yy.shortlink.admin.service.GroupService;
import com.yy.shortlink.admin.utl.RandomGenerator;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public void saveGroup(String groupName) {
        saveGroup(UserContext.getRealName(),groupName);
    }

    @Override
    public void saveGroup(String userName, String groupName) {
        //TODO: repetitive adding,same groupName
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        }while (!hasGid(userName,gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .username(userName)
                .name(groupName)
                .sortOrder(0)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupResDto> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOS = baseMapper.selectList(queryWrapper);
        Result<List<ShortLinkCountQueryRespDTO>> listResult = shortLinkRemoteService.listGroupShortLinkCount(groupDOS.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupResDto> shortLinkGroupRespDTOList = BeanUtil.copyToList(groupDOS, ShortLinkGroupResDto.class);
        shortLinkGroupRespDTOList.forEach(each -> {
            Optional<ShortLinkCountQueryRespDTO> first = listResult.getData().stream()
                    .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                    .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        });
        return shortLinkGroupRespDTOList;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDto requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getGid,requestParam.getGid())
                .eq(GroupDO::getUsername,UserContext.getUsername())
                .eq(GroupDO::getDelFlag,0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO,updateWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortDto> requestParam) {
        requestParam.forEach(each -> {
            GroupDO groupDO = GroupDO.builder()
                    .sortOrder(each.getSortOrder())
                    .build();
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                    .eq(GroupDO::getUsername, UserContext.getUsername())
                    .eq(GroupDO::getGid, each.getGid())
                    .eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO, updateWrapper);
        });
    }

    private boolean hasGid(String username,String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid,gid)
                .eq(GroupDO::getUsername,Optional.ofNullable(username).orElse(UserContext.getUsername()));
        GroupDO hasGroup = baseMapper.selectOne(queryWrapper);
        return hasGroup == null;
    }


}
