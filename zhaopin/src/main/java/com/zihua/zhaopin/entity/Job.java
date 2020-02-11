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

/**
 * @ClassName Job
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/10 2:17
 */
@Data
@Builder
@TableName("job")
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @TableId(type = IdType.AUTO)
    @FieldName(other = OtherEnum.PRIMARY_KEY, length = 10)
    private Integer id;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "岗位名")
    private String name;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "岗位薪资")
    private String salary;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "经验要求")
    private String experience;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "学历要求")
    private String education;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "工作性质")
    private String nature;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "发布时间")
    private String time;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 1000, comment = "职位优势")
    private String job_advantage;
    
    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 5000, comment = "职位描述")
    private String job_desc;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "工作地点详细")
    private String address;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "职位标记：该岗位正在急招xxx")
    private String mark;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "职位唯一标识")
    private String origin_id;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "发布公司名")
    private String company_name;

    @FieldName(other = OtherEnum.NOT_PRIMARY_KEY, length = 50, comment = "发布公司主页")
    private String company_url;
}

