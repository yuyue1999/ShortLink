package com.yy.shortlink.admin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yy.shortlink.admin.dao.entity.GroupDO;
import com.yy.shortlink.admin.dao.mapper.GroupMapper;
import com.yy.shortlink.admin.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
}
