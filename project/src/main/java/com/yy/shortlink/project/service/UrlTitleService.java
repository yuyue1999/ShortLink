package com.yy.shortlink.project.service;

import com.yy.shortlink.project.common.convention.result.Result;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public interface UrlTitleService {
    @SneakyThrows
    String getTitleByUrl(String url) throws IOException;

}
