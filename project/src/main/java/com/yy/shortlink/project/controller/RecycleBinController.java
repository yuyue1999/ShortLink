package com.yy.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.common.convention.result.Results;
import com.yy.shortlink.project.dto.req.*;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.yy.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam){
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }

    @GetMapping("api/short-link/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecyclePageReqDTO requestParam){
        return Results.success(recycleBinService.pageShortLink(requestParam));
    }

    @PostMapping("api/short-link/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam){
        recycleBinService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    @PostMapping("api/short-link/v1/recycle-bin/delete")
    public Result<Void> deleteRecycleBin(@RequestBody RecycleBinDeleteReqDTO requestParam){
        recycleBinService.deleteRecycleBin(requestParam);
        return Results.success();
    }
}
