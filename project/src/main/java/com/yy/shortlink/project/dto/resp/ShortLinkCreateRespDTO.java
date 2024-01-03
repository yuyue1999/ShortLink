package com.yy.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkCreateRespDTO {

    private String gid;

    //private String groupName;

    /**
     * 完整短链接
     */

    private String fullShortUrl;

    /**
     * 原始链接
     */

    private String originUrl;


}
