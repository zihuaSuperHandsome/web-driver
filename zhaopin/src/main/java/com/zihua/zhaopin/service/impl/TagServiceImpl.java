package com.zihua.zhaopin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zihua.zhaopin.dao.TagMapper;
import com.zihua.zhaopin.entity.Tag;
import com.zihua.zhaopin.service.ITagService;
import org.springframework.stereotype.Service;

/**
 * @ClassName JobTagServiceImpl
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/9 16:09
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {
}
