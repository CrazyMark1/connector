package com.mark.orm.connector.v2.statment;

import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.mapper.MapperParameters;
import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.mapping.BaseTypeHandler;
import com.mark.orm.connector.v2.mapping.TypeHandler;
import com.mark.orm.connector.v2.parsing.Parameter;
import com.mark.orm.connector.v2.result.ResultSetHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/5 15:05
 * @QQ: 85104982
 */
public class StatementHandler {
    private final Configuration configuration;
    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        this.resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> List<E> query(MapperRegistory.MapperData mapperData, MapperParameters parameters) {
        Connection connection=null;
        try {
            connection=getConnection();
            String sql=mapperData.getSql();
            PreparedStatement ps=connection.prepareStatement(sql);
            if (null != parameters && parameters.size()>0){
                List<Parameter> params=parameters.getPapameters();
                TypeHandler typeHandler=new BaseTypeHandler();
                for (int i = 0; i < params.size(); i++) {
                    typeHandler.setParameter(ps,i+1, params.get(i));
                }
            }
            return resultSetHandler.handle(ps.executeQuery(),mapperData.getReturnType());
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
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public int update(MapperRegistory.MapperData mapperData, MapperParameters parameters) {
        Connection connection=null;
        try {
            connection=getConnection();
            PreparedStatement ps=connection.prepareStatement(mapperData.getSql());
            if (null != parameters && parameters.size()>0){
                List<Parameter> params=parameters.getPapameters();
                TypeHandler typeHandler=new BaseTypeHandler();
                for (int i = 0; i < params.size(); i++) {
                    typeHandler.setParameter(ps,i+1, params.get(i));
                }
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
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
