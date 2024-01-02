package com.yy.shortlink.admin.controller;

import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.yy.shortlink.admin.common.convention.result.Results;
import com.yy.shortlink.admin.dto.req.UserRegisterReqDto;
import com.yy.shortlink.admin.dto.resp.UserRespDto;
import com.yy.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDto> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserbyUsername(username));
    }
    @GetMapping("api/short-link/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username){
        return Results.success(userService.hasUsername(username));
    }

    @PostMapping("api/short-link/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDto requestParam){
        userService.UserRegister(requestParam);
        return Results.success();
    }
}
