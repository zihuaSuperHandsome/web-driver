package com.zihua.webspider.utils;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HTMLFilter;
import com.google.common.base.CharMatcher;

/**
 * @ClassName HtmlUtil
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 16:39
 */
public class StrUtil {

    public static String htmlFilter(String htmlContent) {
        return (new HTMLFilter()).filter(htmlContent);
    }
    
    public static String subContent(String content, String start, String end, boolean isInclude) {
        if (!isInclude) {
            return subContent(content, start, end);
        } else {
            String reg = StrFormatter.format("{}.*{}", start, end);
            return ReUtil.get(reg, content, 0);
        }
    }
    
    public static String subContent(String content, String start, String end) {
        String reg = StrFormatter.format("({}{}).*({}{})", "?<=", start, "?=", end);
        return ReUtil.get(reg, content, 0);
    }
    
    public static String subContent(String content, String start) {
        String reg = StrFormatter.format("({}{}).*", "?<=", start);
        return ReUtil.get(reg, content, 0);
    }
    
    // 将连续空格替换成单个空格
    public static String space(String content) {
        return CharMatcher.whitespace().trimAndCollapseFrom(content, ' ');
    }



    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static boolean isBlank(CharSequence str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if (!CharUtil.isBlankChar(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }
}
