package com.webdriver.test;

import com.webdriver.entity.Tag;
import com.webdriver.service.ITagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagTest {
    
    @Resource
    private ITagService Service;
    
    @Test
    public void selectList() {
        List<Tag> List = Service.list();
        List.forEach(System.out::println);
    }
}
