package com.yy.shortlink.admin.controller;

import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.common.convention.result.Results;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupInsertReq;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupSortDto;
import com.yy.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDto;
import com.yy.shortlink.admin.dto.resp.ShortLinkGroupResDto;
import com.yy.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> insertGroup(@RequestBody ShortLinkGroupInsertReq requestParam){
        groupService.saveGroup(requestParam.getGroupName());
        return Results.success();
    }

    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupResDto>> listGroup(){

        return Results.success(groupService.listGroup());
    }

    @PutMapping("/api/short-link/admin/v1/group")
    public  Result<Void> update(@RequestBody ShortLinkGroupUpdateReqDto requestParam){

        groupService.updateGroup(requestParam);
        return Results.success();
    }

    @DeleteMapping("/api/short-link/admin/v1/group")
    public  Result<Void> delete(@RequestParam String gid){

        groupService.deleteGroup(gid);
        return Results.success();
    }

    @PostMapping("/api/short-link/admin/v1/group/sort")
    public Result<Void> sortGroup(@RequestBody List<ShortLinkGroupSortDto> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }


}
