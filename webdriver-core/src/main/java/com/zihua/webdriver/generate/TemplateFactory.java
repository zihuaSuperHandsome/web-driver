package com.zihua.webdriver.generate;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TemplateFactory
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/22 13:11
 */
public class TemplateFactory {
    
    private GroupTemplate gt;
    private Map<String, Template> templates = new HashMap<String, Template>();
    
    private void init() {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("templates/");
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gt = new GroupTemplate(resourceLoader, cfg);
    }
    
    public Template getTemplate(String path) {
        init();
        Template var1 = gt.getTemplate(path);
        return var1;
    }
}
