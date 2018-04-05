package com.mark.orm.connector.v2.statment;

import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.result.ResultSetHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/5 15:05
 * @QQ: 85104982
 */
public class StatmentHandler {
    private final Configuration configuration;
    private final ResultSetHandler resultSetHandler;

    public StatmentHandler(Configuration configuration) {
        this.configuration = configuration;
        this.resultSetHandler = new ResultSetHandler(configuration);
    }

    public <T> T query(MapperRegistory.MapperData mapperData, Object parameter) {
        try {
            Connection connection=getConnection();
            PreparedStatement prst=connection.prepareStatement(String.format(mapperData.getSql(),Integer.parseInt(String.valueOf(parameter))));
            return resultSetHandler.handle(prst.executeQuery(),mapperData.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
