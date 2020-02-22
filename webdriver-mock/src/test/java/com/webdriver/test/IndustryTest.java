package com.webdriver.test;

import com.webdriver.entity.Industry;
import com.webdriver.service.IIndustryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndustryTest {
    
    @Resource
    private IIndustryService Service;
    
    @Test
    public void selectList() {
        List<Industry> List = Service.list();
        List.forEach(System.out::println);
    }
}
