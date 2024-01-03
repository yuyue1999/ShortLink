package com.yy.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import com.yy.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ShortLinkService extends IService<ShortLinkDo> {
    public ShortLinkCreateRespDTO createShortLink(@RequestBody ShortLinkCreateReqDTO requesParam);


    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}
