package com.zdn.jdbctemplate.business.dao;

import com.zdn.jdbctemplate.business.dao.entity.AccountPo;

public interface IAccountDao {

    /**
     * 更新账户
     */
    void updateAccount(AccountPo accountPo);

    /**
     * 根据名称查找账户
     * @param name
     * @return
     */
    AccountPo findAccountByName(String name);
}
