package com.yy.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShortLinkCreateReqDTO {

    private String domain;

    /**
     * 原始链接
     */

    private String originUrl;


    /**
     * 分组标识
     */

    private String gid;

    /**
     * 创建类型 0：控制台 1：接口
     */

    private int createdType;

    /**
     * 有效期类型 0：永久有效 1：用户自定义
     */

    private int validDateType;

    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date validDate;

    /**
     * 描述
     */

    private String describe;
}
