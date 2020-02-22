package com.webdriver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.webdriver.entity.Industry;
import com.webdriver.dao.IndustryMapper;
import com.webdriver.service.IIndustryService;


@Service
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IIndustryService {
}
