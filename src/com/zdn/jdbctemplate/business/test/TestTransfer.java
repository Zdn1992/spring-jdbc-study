package com.zdn.jdbctemplate.business.test;

import com.zdn.jdbctemplate.business.service.IAccountService;
import com.zdn.jdbctemplate.business.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTransfer {
    public static void main(String[] args) {
        ApplicationContext ap =
                new ClassPathXmlApplicationContext("resources/ApplicationContext.xml");
        IAccountService accountService = ap.getBean("accountServiceImpl",AccountServiceImpl.class);
        accountService.transfer("wangwu","zhaoliu",10.0);
    }
}
