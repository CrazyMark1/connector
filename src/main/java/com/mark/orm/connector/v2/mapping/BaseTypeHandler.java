package com.mark.orm.connector.v2.mapping;

import com.mark.orm.connector.v2.parsing.Parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/6 16:24
 * @QQ: 85104982
 */
public class BaseTypeHandler implements TypeHandler{
    @Override
    public void setParameter(PreparedStatement ps, int i, Parameter parameter) throws SQLException {
        Class clazz=parameter.getType();
        System.out.println(clazz);
        if (Integer.class==clazz){
            ps.setInt(i,(Integer) parameter.getValue());
        }
        else if (String.class==parameter.getType()){
            ps.setString(i,(String)parameter.getValue());
        }
        else if (Double.class==parameter.getType()){
            ps.setDouble(i,(Double) parameter.getValue());
        }
        else if (Float.class==parameter.getType()){
            ps.setFloat(i,(Float) parameter.getValue());
        }
        else if (Date.class==parameter.getType()){
            ps.setDate(i,(Date)parameter.getValue());
        }
        else if  (Time.class==parameter.getType()){
            ps.setTime(i,(Time)parameter.getValue());
        }
        else {
            throw new RuntimeException("暂不支持该类型");
        }
    }
}
