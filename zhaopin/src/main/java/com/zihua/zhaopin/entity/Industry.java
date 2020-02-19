package com.zihua.zhaopin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zihua.webdriver.annotation.FieldName;
import com.zihua.webdriver.enums.OtherEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName JobTag
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/10 2:30
 */
@Data
@Builder
@TableName("industry")
@AllArgsConstructor
@NoArgsConstructor
public class Industry {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "行业名")
    private String name;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "源id")
    private String origin_id;
}
