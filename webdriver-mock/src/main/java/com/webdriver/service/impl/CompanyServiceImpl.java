package com.webdriver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.webdriver.entity.Company;
import com.webdriver.dao.CompanyMapper;
import com.webdriver.service.ICompanyService;


@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {
}
