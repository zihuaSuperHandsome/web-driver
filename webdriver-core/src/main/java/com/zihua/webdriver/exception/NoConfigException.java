package com.zihua.webdriver.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName NoConfigException
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 16:27
 */
public class NoConfigException extends RuntimeException{
    
    private static Logger log = LoggerFactory.getLogger(NoConfigException.class);

    public NoConfigException() {
    }

    public NoConfigException(String message) {
        super(message);
        log.error(message);
    }
}
