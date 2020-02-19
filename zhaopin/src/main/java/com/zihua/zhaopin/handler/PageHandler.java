package com.zihua.zhaopin.handler;

import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.zihua.webdriver.handler.AbstractChannelHandler;
import com.zihua.webdriver.utils.StrUtil;
import com.zihua.zhaopin.entity.Job;
import com.zihua.zhaopin.service.IJobService;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PageHandle
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 17:35
 */
@Component
public class PageHandler extends AbstractChannelHandler {
    
    @Resource
    IJobService jobService;
    
    @Override
    public boolean condition(Page page) {
//        String reg = "https://.*lagou.com/jobs/.*";
//        return ReUtil.isMatch(reg, page.getUrl().get());
        return false;
    }
    
    @Override
    public void handler(Page page) {
        Html html = page.getHtml();
        
        try {
            
        // 获取唯一标识
        String origin_id = html.$("#jobid", "value").get();
        
        // 获取公司唯一标识
        String company_id = html.$("#companyid", "value").get();
        
        // 获取职位标记(可能需要花费大量本地时间)
        String mark_api_url = String.format("https://gate.lagou.com/v1/zhaopin/positions/%s/topCardDetails", origin_id);
        String request = HttpRequest.get(mark_api_url).header("X-L-REQ-HEADER", "{deviceType: 10}").timeout(20000).execute().body();
        JSONObject json = new JSONObject(request);
        String mark;
        try {
            mark = json.get("content", JSONObject.class, true).getStrEscaped("showText", null);
        } catch (NullPointerException e) {
            mark = null;
        }
        
        // 获取地址
        StringBuilder address = new StringBuilder("");
        address.append(String.join("-", html.$(".job-address .work_addr").xpath("//a/text()").all()));
        address.append("-").append(StrUtil.space(html.$(".job-address .work_addr").xpath("//div/text()").get().replace("-", "")));
        
        // 获取工作信息列表
        List<String> job_requests = html.xpath("//dd[@class='job_request']/h3/span/text()").all();
        
        Job job = Job.builder()
                .name(html.xpath("//div[@class='job-name']/h1[@class='name']/text()").get())
                .salary(job_requests.get(0))
                .experience(ReUtil.get(".*(?=/)", job_requests.get(2), 0))
                .education(ReUtil.get(".*(?=/)", job_requests.get(3), 0))
                .nature(job_requests.get(4))
                .time(html.xpath("//dd[@class='job_request']/p[@class='publish_time']/text()").get())
                .job_advantage(html.$(".job_detail .job-advantage").get())
                .job_desc(html.$(".job_detail .job_bt").get())
                .address(address.toString())
                .mark(mark)
                .origin_id(origin_id)
                .company_id(company_id)
                .company_name(StrUtil.space(html.$(".job_company .job_company_content").xpath("//em[@class='fl-cn']/text()").get()))
                .company_url(html.$(".job_company .c_feature").xpath("//a/h4/text()").get())
            .build();
            jobService.save(job);

        } catch (Exception e) {
            System.out.println(page.getUrl());
            System.out.println(html);
//            page.addTargetRequest(page.getUrl().get());
        }
    }

    @Override
    public String nextTarget(Page page) {
        return String.format("https://www.lagou.com/gongsi/%s.html", page.getHtml().$("#companyid", "value").get());
    }
}
