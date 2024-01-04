package com.yy.shortlink.project.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ShortLinkPageRespDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 域名
     */

    private String domain;

    /**
     * 短链接
     */

    private String shortUri;

    /**
     * 完整短链接
     */

    private String fullShortUrl;

    /**
     * 原始链接
     */

    private String originUrl;


    /**
     * 分组标识
     */

    private String gid;

    /**
     * 启用标识 0：未启用 1：已启用
     */

    private int enableStatus;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;


    private String favicon;
}
