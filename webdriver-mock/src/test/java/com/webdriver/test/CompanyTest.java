package com.webdriver.test;

import com.webdriver.entity.Company;
import com.webdriver.service.ICompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyTest {
    
    @Resource
    private ICompanyService Service;
    
    @Test
    public void selectList() {
        List<Company> List = Service.list();
        List.forEach(System.out::println);
    }
}
