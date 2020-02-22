package com.webdriver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.webdriver.entity.Tag;
import com.webdriver.dao.TagMapper;
import com.webdriver.service.ITagService;


@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
}
