package com.zihua.webdriver.annotation;

import java.lang.annotation.*;

/**
 * @author 刘子华
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DatabaseName {

    String value();
}