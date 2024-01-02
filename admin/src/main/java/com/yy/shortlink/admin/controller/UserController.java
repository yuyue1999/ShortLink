package com.yy.shortlink.admin.controller;

import com.yy.shortlink.admin.common.convention.result.Result;
import com.yy.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.yy.shortlink.admin.common.convention.result.Results;
import com.yy.shortlink.admin.dto.resp.UserRespDto;
import com.yy.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDto> getUserByUsername(@PathVariable("username") String username){
        return Results.success(userService.getUserbyUsername(username));
    }
}
