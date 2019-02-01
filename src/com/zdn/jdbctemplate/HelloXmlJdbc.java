package com.zdn.jdbctemplate;

import com.zdn.jdbctemplate.business.dao.entity.AccountPo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

public class HelloXmlJdbc {

    public static void main(String[] args) {
        ApplicationContext ap =
                new ClassPathXmlApplicationContext("resources/ApplicationContext.xml");
        JdbcTemplate jt = (JdbcTemplate) ap.getBean("jdbcTemplate");
//        query(jt);
        connect(ap);

    }

    private static void connect(ApplicationContext ap){
        DataSource dataSource = ap.getBean("dataSource",DataSource.class);
        // 使用Spring提供的事务同步管理器实现Connection和线程进行绑定
        TransactionSynchronizationManager.initSynchronization();
        Connection connection1 = DataSourceUtils.getConnection(dataSource);
        Connection connection2 = DataSourceUtils.getConnection(dataSource);
        System.out.println(connection1 == connection2);
        System.out.println(connection1);
        new Thread(() -> {
            // 不同的线程绑定的Connection不同
            Connection connection3 = DataSourceUtils.getConnection(dataSource);
            System.out.println(connection3);
        }).start();
    }

    private static void query(JdbcTemplate jt){
        // JdbcTemplate查询操作
        // 查询集合
        List<AccountPo> list = jt.query("select * from it_account where account_money > ?",
                (resultSet, i) -> {
                    AccountPo account = new AccountPo();
                    account.setId(resultSet.getInt("account_id"));
                    account.setName(resultSet.getString("account_name"));
                    account.setMoney(resultSet.getDouble("account_money"));
                    return account;
                }, 100);
        System.out.println("list: " + list);

        // 查询单个对象
        AccountPo account = jt.query("select * from it_account where account_money = ?",
                resultSet -> {
                    AccountPo accountPo = null;
                    if (resultSet.next()){
                        accountPo = new AccountPo();
                        accountPo.setId(resultSet.getInt("account_id"));
                        accountPo.setName(resultSet.getString("account_name"));
                        accountPo.setMoney(resultSet.getDouble("account_money"));
                    }
                    return accountPo;
                }, 13);
        System.out.println("account: " + account);

        // 查询一行一列,多为聚合函数的返回
        int count = jt.queryForObject("select count(*) from it_account where account_money > ?"
                ,Integer.class,50);
        System.out.println("count: " + count);
    }
}
