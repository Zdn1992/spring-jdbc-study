package com.zdn.jdbctemplate.business.dao.impl;

import com.zdn.jdbctemplate.business.dao.IAccountDao;
import com.zdn.jdbctemplate.business.dao.JdbcTemplateSupportDao;
import com.zdn.jdbctemplate.business.dao.entity.AccountPo;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl extends JdbcTemplateSupportDao implements IAccountDao {

    @Override
    public void updateAccount(AccountPo accountPo) {
        String sql = "update it_account set account_name=?,account_money=? where " +
                "account_id = ?";
        getJdbcTemplate().update(sql,accountPo.getName(),accountPo.getMoney(),accountPo.getId());
    }

    @Override
    public AccountPo findAccountByName(String name) {
        return getJdbcTemplate().query(
                "select * from it_account where account_name = ?"
                , resultSet -> {
                    AccountPo accountPo = null;
                    if (resultSet.next()) {
                        accountPo = new AccountPo();
                        accountPo.setId(resultSet.getInt("account_id"));
                        accountPo.setName(resultSet.getString("account_name"));
                        accountPo.setMoney(resultSet.getDouble("account_money"));
                    }
                    return accountPo;
                }
                , name);
    }
}
