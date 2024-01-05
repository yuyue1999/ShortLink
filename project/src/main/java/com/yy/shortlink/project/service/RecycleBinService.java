package com.yy.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import com.yy.shortlink.project.dto.req.*;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface RecycleBinService extends IService<ShortLinkDo> {

    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecyclePageReqDTO requestParam);


    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);

    void deleteRecycleBin(RecycleBinDeleteReqDTO requestParam);
}
