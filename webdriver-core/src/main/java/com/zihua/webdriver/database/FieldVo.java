package com.zihua.webdriver.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FieldVo
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/1 20:35
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FieldVo {

    /**
     *  字段名
     */
    private String name;

    /**
     *  字段类型
     */
    private String type;

    /**
     *  字段长度
     */
    private Integer length;


    /**
     *  字段注释
     */
    private String comment;

    /**
     *  字段其他信息(将会直接拼接到当前行的最后面)
     */
    private String other;
}
