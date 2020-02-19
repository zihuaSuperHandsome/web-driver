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

/**
 * @ClassName Company
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/10 2:46
 */
@Data
@Builder
@TableName("company")
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "公司名")
    private String name;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "标记 是否是认证企业")
    private String mark;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "所属领域")
    private String domain;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "发展阶段")
    private String stage;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "规模")
    private String size;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "首页")
    private String homepage;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "公司地址")
    private String address;
    
    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "封面地址")
    private String cover;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "公司签名")
    private String word;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "正在招聘的职位数量")
    private String count;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "简历处理率")
    private String rate;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 500, comment = "公司介绍")
    private String desc;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "公司信息")
    private String business;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "成立时间")
    private String business_time;
    
    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "注册资本")
    private String business_capital;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "法人代表")
    private String business_legal ;
}
