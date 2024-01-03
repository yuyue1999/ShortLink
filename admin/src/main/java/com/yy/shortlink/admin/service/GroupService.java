package com.yy.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.dao.entity.GroupDO;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupSortDto;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDto;
import com.yy.shortlink.admin.dto.resp.ShortLinkGroupResDto;

import java.util.List;

public interface GroupService extends IService<GroupDO> {

    void saveGroup(String groupName);

    List<ShortLinkGroupResDto> listGroup();

    void updateGroup(ShortLinkGroupUpdateReqDto requestParam);


    void deleteGroup(String gid);

    void sortGroup(List<ShortLinkGroupSortDto> requestParam);
}
