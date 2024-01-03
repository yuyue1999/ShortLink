package com.yy.shortlink.project.controller;

import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.common.convention.result.Results;
import com.yy.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.yy.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    @PostMapping("api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requesParam){

        return Results.success(shortLinkService.createShortLink(requesParam));
    }
}
