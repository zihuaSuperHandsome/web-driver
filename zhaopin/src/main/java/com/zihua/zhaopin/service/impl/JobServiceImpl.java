package com.zihua.zhaopin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zihua.zhaopin.dao.JobMapper;
import com.zihua.zhaopin.dao.TagMapper;
import com.zihua.zhaopin.entity.Job;
import com.zihua.zhaopin.entity.Tag;
import com.zihua.zhaopin.service.IJobService;
import com.zihua.zhaopin.service.ITagService;
import org.springframework.stereotype.Service;

/**
 * @ClassName JobTagServiceImpl
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 16:09
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {
}
