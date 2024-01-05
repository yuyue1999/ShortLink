package com.yy.shortlink.project.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import lombok.Data;

import java.util.List;

@Data
public class ShortLinkRecyclePageReqDTO extends Page<ShortLinkDo> {
    private List<String> gidList;
}
