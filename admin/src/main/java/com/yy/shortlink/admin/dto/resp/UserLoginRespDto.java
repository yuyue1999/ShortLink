package com.yy.shortlink.admin.dto.resp;

import lombok.Data;

@Data
public class UserLoginRespDto {
    private String token;

    public UserLoginRespDto(String uuid) {
        token = uuid;
    }
}
