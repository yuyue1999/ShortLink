package com.yy.shortlink.project.dto.req;

import lombok.Data;

@Data
public class RecycleBinDeleteReqDTO {
    private String gid;

    private String fullShortUrl;
}
