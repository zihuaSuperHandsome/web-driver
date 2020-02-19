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
@TableName("book_yiduck_catalog")
@AllArgsConstructor
@NoArgsConstructor
public class BookCatalog {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(length = 100, comment = "章节标题")
    private String title;

    @FieldName(length = 100, comment = "章节地址")
    private String url;

    @FieldName(length = 50, comment = "章节源id")
    private String origin_id;

    @FieldName(length = 0, comment = "文章内容")
    private String content;

    @FieldName(length = 100, comment = "作者")
    private String author;

    @FieldName(length = 100, comment = "章节标题")
    private String content_title;
}
