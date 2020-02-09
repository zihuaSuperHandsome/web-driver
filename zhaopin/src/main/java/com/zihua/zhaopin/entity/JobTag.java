package com.zihua.zhaopin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zihua.webspider.annotation.FieldName;
import com.zihua.webspider.enums.OtherEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("job_lagou_tags")
@AllArgsConstructor
@NoArgsConstructor
public class JobTag {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "标签名")
    private String name;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 300, comment = "标签地址")
    private String url;
    
    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "父标签名")
    private String p_name;

    @FieldName(length = 300, comment = "源地址标识")
    private String origin_id;
}
