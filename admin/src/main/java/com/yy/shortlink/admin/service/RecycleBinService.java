package com.yy.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkRecyclePageReqDTO;
import com.yy.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

public interface RecycleBinService {


    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecyclePageReqDTO requestParam);
}
