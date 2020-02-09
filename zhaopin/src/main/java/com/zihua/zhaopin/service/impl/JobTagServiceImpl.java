package com.zihua.zhaopin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zihua.zhaopin.dao.JobTagMapper;
import com.zihua.zhaopin.entity.JobTag;
import com.zihua.zhaopin.service.IJobTagService;
import org.springframework.stereotype.Service;

/**
 * @ClassName JobTagServiceImpl
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 16:09
 */
@Service
public class JobTagServiceImpl extends ServiceImpl<JobTagMapper, JobTag> implements IJobTagService {
}
