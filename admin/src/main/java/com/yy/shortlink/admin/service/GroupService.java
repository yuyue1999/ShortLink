package com.yy.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.admin.dao.entity.GroupDO;

public interface GroupService extends IService<GroupDO> {

    void saveGroup(String groupName);
}
