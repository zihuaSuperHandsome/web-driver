package com.zihua.webdriver.downloader;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SimpleDownloader
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/11 23:52
 */
public class SimpleDownloader implements Downloader {

    @Override
    public Page download(Request request, Task task) {
        
        // 通过url1获取到合法的cookie
        String url1 = "https://www.lagou.com/jobs/list_?city=%E4%B8%8A%E6%B5%B7&cl=false&fromSearch=true&labelWords=&suginput=";
        Map<String, String> headers1 = new HashMap<String, String>(){
            {
                put("Connection", "keep-alive");
                put("Cache-Control", "max-age=0");
                put("Upgrade-Insecure-Requests", "1");
                put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
                put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                put("Accept-Encoding", "gzip, deflate, br");
                put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            }
        };

        Map<String, String> headers2 = new HashMap<String, String>(){
            {
                put("Origin", "https://www.lagou.com");
                put("X-Anit-Forge-Code", "0");
                put("Accept-Encoding", "gzip, deflate, br");
                put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
                put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
                put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                put("Accept", "application/json, text/javascript, */*; q=0.01");
                put("Referer", "https://www.lagou.com/jobs/list_java?px=new&city=%E4%B8%8A%E6%B5%B7");
                put("X-Requested-With", "XMLHttpRequest");
                put("Connection", "keep-alive");
                put("X-Anit-Forge-Token", "None");
            }
        };
        
        Map<String, String> cookies2 = new HashMap<String, String>(){
            {
                put("X_MIDDLE_TOKEN", "797bc148d133274a162ba797a6875817");
                put("JSESSIONID", "ABAAABAAAIAACBI03F33A375F98E05C5108D4D742A34114");
                put("_ga", "GA1.2.1912257997.1548059451");
                put("_gat", "1");
                put("Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6", "1548059451");
                put("user_trace_token", "20190121163050-dbd72da2-1d56-11e9-8927-525400f775ce");
                put("LGSID", "20190121163050-dbd72f67-1d56-11e9-8927-525400f775ce");
                put("PRE_UTM", "");
                put("PRE_HOST", "");
                put("PRE_SITE", "");
                put("PRE_LAND", "https%3A%2F%2Fwww.lagou.com%2F%3F_from_mid%3D1");
                put("LGUID", "20190121163050-dbd73128-1d56-11e9-8927-525400f775ce");
                put("_gid", "GA1.2.1194828713.1548059451");
                put("index_location_city", "%E5%85%A8%E5%9B%BD");
                put("TG-TRACK-CODE", "index_hotjob");
                put("LGRID", "20190121163142-fb0cc9c0-1d56-11e9-8928-525400f775ce");
                put("Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6", "1548059503");
                put("SEARCH_ID", "86ed37f5d8da417dafb53aa25cd6fbc0");
            }
        };

        HttpResponse httpResponse1 = HttpRequest.get(url1).addHeaders(headers1).timeout(20000).execute();
        httpResponse1.getCookies().forEach((c) -> cookies2.put(c.getName(), c.getValue()));
        StringBuffer cookie = new StringBuffer();
        cookies2.forEach((s, s2) -> cookie.append(s).append("=").append(s2).append(";"));
        
        headers2.put("Cookie", cookie.toString());
        String url2 = "https://www.lagou.com/jobs/positionAjax.json";
        HttpResponse httpResponse2 = HttpRequest.get(url2).addHeaders(headers2).timeout(20000).execute();
        // 通过合法cookie获取查询接口
        
        System.out.println("========================================================");
        System.out.println(httpResponse2.body());
        System.out.println("========================================================");
        return Page.fail();
    }

    public static void main(String[] args) {
        String url = "https://www.lagou.com/jobs/positionAjax.json";
        for (int i = 0; i < 10; i++) {
            HttpResponse httpResponse = HttpRequest.post(url).timeout(20000).execute();
            System.out.format("========================第%d个============================\n", i);
            System.out.println(httpResponse.body());
            System.out.println("========================================================");
        }
    }

    @Override
    public void setThread(int i) {

    }
}
