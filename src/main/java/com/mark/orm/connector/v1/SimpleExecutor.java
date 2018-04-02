package com.mark.orm.connector.v1;

import java.sql.*;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:13
 * @QQ: 85104982
 */
public class SimpleExecutor implements Executor {
    public <T> T query(String statement, String parameter) {
        Connection connection=null;
        PreparedStatement prst=null;
        Test test=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "123456");
            String sql=String.format(statement,Integer.parseInt(parameter));
            prst=connection.prepareStatement(sql);
            ResultSet rs=prst.executeQuery();
            while (rs.next()){
                test=new Test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (null!=connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return (T)test;
    }
}
