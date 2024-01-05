package com.yy.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.common.convention.result.Results;
import com.yy.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.yy.shortlink.admin.remote.dto.req.*;
import com.yy.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.yy.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam){
        shortLinkRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }


    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecyclePageReqDTO requestParam) {
        return recycleBinService.pageRecycleBinShortLink(requestParam);
    }


    @PostMapping("api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam){
        shortLinkRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    @DeleteMapping("api/short-link/admin/v1/recycle-bin/delete")
    public Result<Void> deleteRecycleBin(@RequestBody RecycleBinDeleteReqDTO requestParam){
        shortLinkRemoteService.deleteRecycleBin(requestParam);
        return Results.success();
    }
}
