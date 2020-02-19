package com.zihua.webdriver.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zihua.webdriver.test.dao.BookMapper;
import com.zihua.webdriver.test.entity.BookBean;
import com.zihua.webdriver.test.service.IBookService;
import org.springframework.stereotype.Service;

/**
 * @ClassName VideoServiceImpl
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/1 13:58
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, BookBean> implements IBookService {


}
