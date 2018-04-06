package com.mark.orm.connector.v2.mapping;

import com.mark.orm.connector.v2.parsing.Parameter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/6 16:21
 * @QQ: 85104982
 */
public interface TypeHandler {

    void setParameter(PreparedStatement ps, int i, Parameter parameter) throws SQLException;

}
