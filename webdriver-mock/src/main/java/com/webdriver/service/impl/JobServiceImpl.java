package com.webdriver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.webdriver.entity.Job;
import com.webdriver.dao.JobMapper;
import com.webdriver.service.IJobService;


@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {
}
