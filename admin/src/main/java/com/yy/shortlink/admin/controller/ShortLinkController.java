package com.yy.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.common.convention.result.Results;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.yy.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.yy.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.*;
import com.yy.shortlink.admin.remote.dto.ShortLinkRemoteService;
@RestController
public class ShortLinkController {
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }
}
