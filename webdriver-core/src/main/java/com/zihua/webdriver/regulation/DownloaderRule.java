package com.zihua.webdriver.regulation;

import us.codecraft.webmagic.Request;

import java.util.Map;

public interface DownloaderRule {
    
    boolean condition(Request request);
    
    Map<String, String> cookies(Request request);

    Map<String, String> headers(Request request);
}
