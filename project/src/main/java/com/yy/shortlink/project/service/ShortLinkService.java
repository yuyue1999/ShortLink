package com.yy.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.dao.entity.ShortLinkDo;
import com.yy.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.yy.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkCountQueryRespDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.yy.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public interface ShortLinkService extends IService<ShortLinkDo> {
    public ShortLinkCreateRespDTO createShortLink(@RequestBody ShortLinkCreateReqDTO requesParam);


    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    List<ShortLinkCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response) throws IOException;

}
