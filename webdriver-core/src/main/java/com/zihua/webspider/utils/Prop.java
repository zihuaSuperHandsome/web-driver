package com.zihua.webspider.utils;

import cn.hutool.core.convert.Convert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @ClassName Prop
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/6 2:35
 */
public class Prop extends Properties {

    private String url;

    public Prop(String url) {
        try {
            
            super.load(new InputStreamReader(ClassLoader.getSystemResourceAsStream(url), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return super.getProperty(key);
    }


    public Boolean getBool(String key, Boolean defaultValue) {
        return Convert.toBool(this.getStr(key), defaultValue);
    }

    public Boolean getBool(String key) {
        return this.getBool((String)key, (Boolean)null);
    }

    public String getStr(String key) {
        return super.getProperty(key);
    }

    public String getStr(String key, String defaultValue) {
        return super.getProperty(key, defaultValue);
    }
}
