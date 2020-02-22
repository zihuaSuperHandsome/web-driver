package com.zihua.webdriver.generate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.NoResourceException;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.zihua.webdriver.utils.Prop;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @ClassName StandardCodeGenerator
 * @Description TODO    标准代码生成类
 * @Author 刘子华  
 * @Date 2020/2/17 19:19
 */
public class Generate implements Serializable{
    
    private static Logger log = LoggerFactory.getLogger(Generate.class);

    private String baseName;
    private String basePath;
    private String scanPath;
    private String mapperPath;
    private String servicePath;
    private String serviceImplPath;
    private boolean isCovered = false;
    private boolean testToggle = true;
    private String testPath;
    
    private Set<Class<?>> classes = new HashSet<>();

    // 加载配置项
    private void initConfig() {
        Prop config = null;
        try {
            config = new Prop("config.properties");
        } catch (NoResourceException e) {
            log.error("无法加载到主文件{}.", "config.properties");
        }
        assert config != null;
        this.baseName = config.getStr("generate.base-name");
        this.basePath = config.getStr("generate.base-path");
        this.scanPath = config.getStr("generate.scan-path");
        this.mapperPath = config.getStr("generate.mapper-path", this.basePath + ".mapper");
        this.servicePath = config.getStr("generate.service-path", this.basePath + ".service");
        this.serviceImplPath = this.servicePath.concat(".impl");
        this.isCovered = config.getBool("generate.isCovered", false);
        this.testToggle = config.getBool("generate.test-toggle", false);
        this.testPath = config.getStr("generate.test-path", this.basePath + ".test");
    }
    
    // 初始化组件
    private void initComponent() {
        this.beanScan(this.scanPath);
    }
    
    // 获取上下文模板，与template.renader配合使用，否则会抛出Queue full.
    private Template getTemplate(String key) {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader("templates/");
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        return gt.getTemplate(key);
    }
    
    private String getBasePath() {
        String root = System.getProperty("user.dir");
        File f = new File(root + "\\" + this.baseName);
        if (f.exists()) {
            root = root + "\\" + this.baseName;
        }
        String url = root + "\\src\\main\\java\\" + this.basePath.replace(".", "\\");
        return url;
    }
    
    private String getTestPath() {
        String root = System.getProperty("user.dir");
        File f = new File(root + "\\" + this.baseName);
        if (f.exists()) {
            root = root + "\\" + this.baseName;
        }
        String url = root + "\\src\\test\\java\\" + this.basePath.replace(".", "\\");
        return url;
    }

    // 扫描实体类
    private void beanScan(String path) {
        Set<Class<?>> set = ClassUtil.scanPackage(path);
        for (Class<?> clazz : set) {
            if (-1 == StrUtil.lastIndexOfIgnoreCase(clazz.getName(), "$")) {
                classes.add(clazz);
            }
        }
    }

    // 生成Mapper
    private void generateMapper(Class<?> clazz) {
        Template var1 = getTemplate("/mapper-template.btl");
        var1.binding(Dict.create().set("mapperPath", this.mapperPath).set("packageName", clazz.getPackage().getName()).set("beanName", clazz.getSimpleName()));
        String text = var1.render();
        String url = getBasePath();
        if (!new File(url).exists()) {
            log.error("根目录不存在:{}", url);
            return;
        }

        String path = ReUtil.get(this.basePath + "\\.(.*)", this.mapperPath, 1);
        if (StringUtils.isNotBlank(path)) {
            path = path.replace(".", "/");
        }
        
        String var2 = url + "/" + path + "/" + clazz.getSimpleName() + "Mapper.java";
        try {
            if (!this.isCovered) {
                File f = new File(var2);
                if (f.exists()) {
                    return;
                }
            }
            OutputStream w = new FileOutputStream(FileUtil.touch(var2));
            w.write(text.getBytes());
            w.close();
            log.info("Success:{}.", var2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 生成service
    private void generateService(Class<?> clazz) {
        Template var1 = getTemplate("/service-template.btl");
        var1.binding(Dict.create().set("servicePath", this.servicePath).set("packageName", clazz.getPackage().getName()).set("beanName", clazz.getSimpleName()));
        String text1 = var1.render();
        
        String url = getBasePath();
        if (!new File(url).exists()) {
            log.error("根目录不存在:{}", url);
            return;
        }

        String path = ReUtil.get(this.basePath + "\\.(.*)", this.servicePath, 1);
        if (StringUtils.isNotBlank(path)) {
            path = path.replace(".", "/");
        }
        
        String var2 = url + "/" + path + "/I" + clazz.getSimpleName() + "Service.java";
        try {
            if (!this.isCovered) {
                File f = new File(var2);
                if (f.exists()) {
                    return;
                }
            }
            OutputStream w = new FileOutputStream(FileUtil.touch(var2));
            w.write(text1.getBytes());
            w.close();
            log.info("Success:{}.", var2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 生成serviceImpl
    private void generateServiceImpl(Class<?> clazz) {
        Template var1 = getTemplate("/service-impl-template.btl");
        var1.binding(Dict.create().set("serviceImplPath", this.serviceImplPath).set("servicePath", this.servicePath).set("mapperPath", this.mapperPath).set("packageName", clazz.getPackage().getName()).set("beanName", clazz.getSimpleName()));
        String text1 = var1.render();
        String url = getBasePath();
        if (!new File(url).exists()) {
            log.error("根目录不存在:{}", url);
            return;
        }

        String path = ReUtil.get(this.basePath + "\\.(.*)", this.serviceImplPath, 1);
        if (StringUtils.isNotBlank(path)) {
            path = path.replace(".", "/");
        }
        
        String var2 = url + "/" + path + "/" + clazz.getSimpleName() + "ServiceImpl.java";
        try {
            if (!this.isCovered) {
                File f = new File(var2);
                if (f.exists()) {
                    return;
                }
            }
            OutputStream w = new FileOutputStream(FileUtil.touch(var2));
            w.write(text1.getBytes());
            w.close();
            log.info("Success:{}.", var2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // 生成测试类地址
    private void generateTest(Class<?> clazz) {
        Template var1 = getTemplate("/junit-test-template.btl");
        var1.binding(Dict.create().set("testPath", this.testPath).set("scanPath", this.scanPath).set("servicePath", this.servicePath).set("beanName", clazz.getSimpleName()));
        String text = var1.render();

        String url = getTestPath();
        if (!new File(url).exists()) {
            log.error("根目录不存在:{}", url);
            return;
        }

        String path = ReUtil.get(this.basePath + "\\.(.*)", this.testPath, 1);
        if (StringUtils.isNotBlank(path)) {
            path = path.replace(".", "/");
        }

        String var2 = url + "/" + path + "/" + clazz.getSimpleName() + "Test.java";
        try {
            if (!this.isCovered) {
                File f = new File(var2);
                if (f.exists()) {
                    return;
                }
            }
            OutputStream w = new FileOutputStream(FileUtil.touch(var2));
            w.write(text.getBytes());
            w.close();
            log.info("Success:{}.", var2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Generate create() {
        return new Generate();
    }
    
    public void generator() {
        this.initConfig();
        this.initComponent();
        // 可以使用线程池提交任务节省时间
        for (Class<?> bean : classes) {
            this.generateMapper(bean);
            this.generateService(bean);
            this.generateServiceImpl(bean);
            if (this.testToggle) {
                this.generateTest(bean);
            }
        }
    }
}
