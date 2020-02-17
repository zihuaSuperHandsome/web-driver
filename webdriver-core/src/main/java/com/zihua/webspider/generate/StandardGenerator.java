package com.zihua.webspider.generate;

/**
 * @ClassName StandardCodeGenerator
 * @Description TODO    标准代码生成类
 * @Author 刘子华  
 * @Date 2020/2/17 19:19
 */
public class StandardGenerator {

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
    
    
    public static void generator() {
        
    } 
    
}
