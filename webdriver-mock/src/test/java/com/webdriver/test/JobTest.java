package com.webdriver.test;

import com.webdriver.entity.Job;
import com.webdriver.service.IJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobTest {
    
    @Resource
    private IJobService Service;
    
    @Test
    public void selectList() {
        List<Job> List = Service.list();
        List.forEach(System.out::println);
    }
}
