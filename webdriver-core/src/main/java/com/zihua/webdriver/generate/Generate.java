package com.zihua.webdriver.generate;

import cn.hutool.core.io.resource.NoResourceException;
import com.zihua.webdriver.utils.Prop;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName StandardCodeGenerator
 * @Description TODO    标准代码生成类
 * @Author 刘子华  
 * @Date 2020/2/17 19:19
 */
@ToString
public class Generate {

    private static Logger log = LoggerFactory.getLogger(Generate.class);
    
    private String scanPath;
    private String basePath;
    private String mapperPath;
    private String servicePath;
    private String serviceImplPath;
    private boolean isCovered = true;
    private boolean testToggle = true;
    private String testPath;
    
    private Prop config;
    
    private void initConfig() {
        try {
            config = new Prop("config.properties");
        } catch (NoResourceException e) {
            log.error("无法加载到主文件{}.", "config.properties");
        }
        this.basePath = config.getStr("generate.base-path");
        this.scanPath = config.getStr("generate.scan-path");
        this.mapperPath = config.getStr("generate.mapper-path", this.basePath + ".mapper");
        this.servicePath = config.getStr("generate.service-path", this.basePath + ".service");
        this.serviceImplPath = this.servicePath.concat(".impl");
        this.isCovered = config.getBool("generate.isCovered", true);
        this.testToggle = config.getBool("generate.test-toggle", true);
        this.testPath = config.getStr("generate.test-path", this.basePath + ".test");
    }
    
    private void initComponent() {
        
    }
    
    public static Generate create() {
        return new Generate();
    }
    
    public void generator() {
        this.initConfig();
        System.out.println(this);
    } 
}
