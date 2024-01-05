package com.yy.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.common.convention.result.Results;
import com.yy.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkRecyclePageReqDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.yy.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
