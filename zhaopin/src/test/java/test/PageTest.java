package test;

import cn.hutool.core.thread.ThreadUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.http.cookie.Cookie;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.thread.CountableThreadPool;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName PageTest
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/17 11:54
 */
public class PageTest implements PageProcessor {
    
    private Site site = Site.me().setRetrySleepTime(1).setSleepTime(300).setTimeOut(3000);
    
    @Override
    public void process(Page page) {
        synchronized (this) {
            System.out.println("================================================================");
            System.out.println("page text:");
            System.out.println(page.getHtml());
            System.out.println("================================================================");
            System.out.println("headers");
            page.getHeaders().forEach((s, strings) -> System.out.println(s + "=" + strings));
            System.out.println("================================================================");
        }
    }

    @Override
    public Site getSite() {
        return site.addHeader("Cookie", "");
    }

    public static void main(String[] args) {
        List<String> urls = Lists.newArrayList(
                "https://www.lagou.com/jobs/3111818.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5603397.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6742174.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5552135.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6811184.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/1207717.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5935213.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6806160.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6124096.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6669810.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/5313751.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6224362.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6776209.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6663936.html?show=39ae8745abb64a539555e13be09e5352",
                "https://www.lagou.com/jobs/6224383.html?show=39ae8745abb64a539555e13be09e5352"
        );

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < urls.size(); i++) {
            int finalI = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        Spider.create(new PageTest()).addUrl(urls.get(finalI)).run();    
                    }
                }
            });
        }
        fixedThreadPool.shutdown();
    }
}
