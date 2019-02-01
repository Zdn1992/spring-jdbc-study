package com.zdn.jdbctemplate.business.service.impl;

import com.zdn.jdbctemplate.business.dao.IAccountDao;
import com.zdn.jdbctemplate.business.dao.entity.AccountPo;
import com.zdn.jdbctemplate.business.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountServiceImpl")
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public void transfer(String originName, String targetName, Double money) {
        // 根据名称查询账户
        AccountPo origin = accountDao.findAccountByName(originName);
        AccountPo target = accountDao.findAccountByName(targetName);
        // 执行转账逻辑
        origin.setMoney(origin.getMoney() - money);
        target.setMoney(target.getMoney() + money);

        accountDao.updateAccount(origin);
        int i = 1 / 0;
        accountDao.updateAccount(target);
    }


}
