package com.zihua.webdriver.processor;

import com.zihua.webdriver.regulation.DownloaderRule;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @ClassName SpiderEx
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/12 23:02
 */
public class SpiderEx extends Spider {
    
    private List<DownloaderRule> ruleList = Lists.newArrayList();
    
    public SpiderEx(PageProcessor pageProcessor) {
        super(pageProcessor);
    }
    
    public List<DownloaderRule> ruleList() {
        return this.ruleList;
    }
    
    public void addRule(DownloaderRule rule) {
        this.ruleList.add(rule);
    }
}
