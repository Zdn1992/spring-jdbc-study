package com.zdn.jdbctemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


public class HelloJdbcTemplate {
    public static void main(String[] args) {
        // c3p0数据源
        ComboPooledDataSource dataSource =
                new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/dev");
            dataSource.setUser("root");
            dataSource.setPassword("");
            JdbcTemplate jt = new JdbcTemplate(dataSource);
            jt.execute("select * from it_account");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
