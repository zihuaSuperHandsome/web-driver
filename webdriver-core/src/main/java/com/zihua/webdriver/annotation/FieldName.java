package com.zihua.webdriver.annotation;

import com.zihua.webdriver.enums.OtherEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldName {

    // 字段定义长度
    int length();

    // 字段注释
    String comment() default "";

    // 其他信息
    OtherEnum other() default OtherEnum.NOT_PRIMARY_KEY;
}
