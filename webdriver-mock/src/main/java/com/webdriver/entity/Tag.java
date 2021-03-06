package com.webdriver.entity;

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
@TableName("tags")
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "Job标签名")
    private String name;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 300, comment = "标签地址")
    private String url;
    
    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "父标签名")
    private String p_name;

    @FieldName(length = 300, comment = "源地址标识")
    private String origin_id;

    @FieldName(length = 100, comment = "备注")
    private String note;
}
