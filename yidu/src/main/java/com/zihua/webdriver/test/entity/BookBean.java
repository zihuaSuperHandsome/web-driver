package com.zihua.webdriver.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zihua.webdriver.annotation.FieldName;
import com.zihua.webdriver.enums.OtherEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("book_yiduck")
@AllArgsConstructor
@NoArgsConstructor
public class BookBean {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(length = 100, comment = "文章标题")
    private String title;

    @FieldName(length = 100, comment = "文章作者")
    private String author;

    @FieldName(length = 100, comment = "文章目录地址")
    private String url;

    @FieldName(length = 50, comment = "文章归类")
    private String type;

    @FieldName(length = 50, comment = "文章状态")
    private String status;

    @FieldName(length = 50, comment = "字段源标识")
    private String origin_id;
}
