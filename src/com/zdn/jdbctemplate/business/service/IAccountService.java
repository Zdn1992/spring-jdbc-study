package com.zdn.jdbctemplate.business.service;


/**
 * 账户业务层方法
 */
public interface IAccountService {

    /**
     *
     * @param originName 转出账户
     * @param targetName 转入账户
     * @param money 转出金额
     */
    void transfer(String originName,String targetName,Double money);



}
