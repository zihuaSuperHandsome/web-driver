package com.zihua.zhaopin.rule;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.zihua.webspider.regulation.DownloaderRule;
import org.assertj.core.util.Maps;
import us.codecraft.webmagic.Request;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName PageRule
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/12 22:17
 */
public class PageRule implements DownloaderRule {
    
    @Override
    public boolean condition(Request request) {
        String reg = "https://.*lagou.com/jobs/.*";
        return ReUtil.isMatch(reg, request.getUrl());
    }

    @Override
    public Map<String, String> cookies(Request request) {
        return Maps.newHashMap("Cookie", "JSESSIONID=;");
    }

    @Override
    public Map<String, String> headers(Request request) {
        return null;
    }
}
