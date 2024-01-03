package com.yy.shortlink.admin.controller;

import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.common.convention.result.Results;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupInsertReq;
import com.yy.shortlink.admin.dto.resp.ShortLinkGroupResDto;
import com.yy.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/api/short-link/v1/group")
    public Result<Void> insertGroup(@RequestBody ShortLinkGroupInsertReq requestParam){
        groupService.saveGroup(requestParam.getGroupName());
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupResDto>> listGroup(){

        return Results.success(groupService.listGroup());
    }


}
