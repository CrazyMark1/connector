package com.mark.orm.connector.v2.result;

import com.mark.orm.connector.v2.config.Configuration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/5 15:06
 * @QQ: 85104982
 */
public class ResultSetHandler {
    private final Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> handle(ResultSet rs, Class type) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<E> list=new ArrayList<E>();
        while (rs.next()) {
            Object resultObj = type.newInstance();
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, rs);
            }
            list.add((E) resultObj);
        }
        return list;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field,rs));
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        //TODO type handles
        //bean属性的名字必须要和数据库column的名字一样
        Class<?> type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        else if(String.class == type){
            return rs.getString(field.getName());
        }
        else if (Double.class==type){
            return rs.getDouble(field.getName());
        }
        else if (Float.class==type){
            return rs.getFloat(field.getName());
        }
        else if (Date.class==type){
            return rs.getDate(field.getName());
        }
        else if  (Time.class==type){
            return rs.getTime(field.getName());
        }
        else {
            throw new RuntimeException("暂不支持该结果映射");
        }
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
