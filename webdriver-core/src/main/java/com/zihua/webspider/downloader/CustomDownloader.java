package com.zihua.webspider.downloader;

import cn.hutool.http.HttpUtil;
import com.zihua.webspider.processor.PageProcessorImpl;
import com.zihua.webspider.processor.SpiderEx;
import com.zihua.webspider.regulation.DownloaderRule;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @ClassName CustomDownloader
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/12 21:38
 */
public class CustomDownloader extends HttpClientDownloader implements Downloader {
    
    @Override
    public Page download(Request request, Task task) {
        
        // 如何把rule传过来？
        DownloaderRule rule = null;
        try {
            SpiderEx s = (SpiderEx) task;
            for (DownloaderRule r : s.ruleList()) {
                if (r.condition(request)) {
                    rule = r;
                    break;
                }
            }
        } catch (ClassCastException e) {
            return super.download(request, task);
        }
        
        if (null != rule && rule.condition(request)) {
            if (null != rule.headers(request)) {
                rule.headers(request).forEach(request::addHeader);
            }
            if (null != rule.cookies(request)) {
                rule.cookies(request).forEach(request::addCookie);
            }
        }

        // 构造request
        return super.download(request, task);
    }

    public static void main(String[] args) throws Exception{
        /* init client */
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        
        
        for (int i = 1; i <= 10; i++) {
            /* do stuff */
            HttpGet get = new HttpGet("https://www.lagou.com/jobs/5580969.html");
            get.addHeader("Cookie", "JSESSIONID=;");
            get.addHeader("Connection", "keep-alive");
            get.addHeader("Upgrade-Insecure-Requests", "1");
            get.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");
            get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            get.addHeader("Accept-Encoding", "gzip, deflate, br");
            get.addHeader("Accept-Language", "zh-CN,zh;q=0.9");

            /* request */
            CloseableHttpResponse response = httpClient.execute(get);

//        String result = EntityUtils.toString(response.getEntity(), "utf-8");
//        System.out.println(result);

            System.out.println("=================================================");
            System.out.format("第%d次访问：\n", i);
            cookieStore.getCookies().forEach(System.out::println);
            System.out.println("=================================================");
            
            Thread.sleep(500);
        }
    }
}
