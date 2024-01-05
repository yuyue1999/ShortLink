package com.yy.shortlink.project.controller;

import com.yy.shortlink.project.common.convention.exception.ServiceException;
import com.yy.shortlink.project.common.convention.result.Result;
import com.yy.shortlink.project.common.convention.result.Results;
import com.yy.shortlink.project.service.UrlTitleService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    private final UrlTitleService urlTitleService;


    @GetMapping("/api/short-link/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        String title = null;
        try {
            title =  urlTitleService.getTitleByUrl(url);
        } catch (IOException e) {
            throw new ServiceException(e.toString());
        }
        return Results.success(title);

    }
}
