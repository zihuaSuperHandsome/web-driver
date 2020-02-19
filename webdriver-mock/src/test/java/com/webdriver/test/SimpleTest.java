package com.webdriver.test;

import com.zihua.webdriver.generate.Generate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName SimpleTest
 * @Description TODO
 * @Author 刘子华
 * @Date 2020/2/19 17:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
    // 1. 我想别人怎么用？
    // StandardGenerator.generator("com.zihua.entity.*");
    // StandardGenerator.generator("com.zihua.entity.User");
    // StandardGenerator.generator(User.class);
    // 调用以上代码可以实现自动生成Mapper、Service、ServiceImpl

    // 2.是否需要配置？
    // generate.base-path=...       # 生成路径地址路径(必选)
    // generate.mapper-path=...     # 生成的mapper地址(可选)，不填则会生成之#{base-path}/mapper
    // generate.service-path=...    # 生成的service地址(可选)，不填则会生成之#{base-path}/service和#{base-path}/service/impl

    // 3.是否需要生成SpringBoot测试类？
    // generate.test-toggle=...     # 是否需要生成测试类
    // generate.test-path=...       # 测试类地址(可选)
    @Test
    public void test() {
        Generate.create().generator();
    }
}
