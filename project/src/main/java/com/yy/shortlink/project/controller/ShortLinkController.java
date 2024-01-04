package com.yy.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.common.convention.result.Results;
import com.yy.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkCountQueryRespDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.yy.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    @PostMapping("api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requesParam){

        return Results.success(shortLinkService.createShortLink(requesParam));
    }

    @GetMapping("api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam){
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }

    @GetMapping("api/short-link/v1/count")
    public Result<List<ShortLinkCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam){
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }

    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }




}
