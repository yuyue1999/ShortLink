package com.yy.shortlink.admin.remote.dto.req;

import lombok.Data;

@Data
public class RecycleBinDeleteReqDTO {
    private String gid;

    private String fullShortUrl;
}
