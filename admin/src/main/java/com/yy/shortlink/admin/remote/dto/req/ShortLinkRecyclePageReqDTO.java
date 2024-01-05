package com.yy.shortlink.admin.remote.dto.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class ShortLinkRecyclePageReqDTO extends Page {
    private List<String> gidList;
}
