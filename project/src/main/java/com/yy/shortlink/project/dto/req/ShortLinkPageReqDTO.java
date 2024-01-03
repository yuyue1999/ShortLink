package com.yy.shortlink.project.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import lombok.Data;

/**
 * 短链接分页请求参数
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDo> {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 排序标识
     */
    //private String orderTag;
}

