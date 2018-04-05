package com.mark.orm.connector.v2.mapper;

import com.mark.orm.connector.bean.Test;
import com.mark.orm.connector.v2.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:28
 * @QQ: 85104982
 */
public class MapperRegistory {
    public static final Map<String,MapperData> METHODSQLMAPPING=new ConcurrentHashMap<String, MapperData>();

    public MapperRegistory() {
        METHODSQLMAPPING.put("com.mark.orm.connector.mapper.TestMapper.selectByPrimaryKey",
                new MapperData("select * from test where id = %d",Test.class));
    }

    public <T>MapperData getMapperData(String nameSpace){
        return METHODSQLMAPPING.get(nameSpace);
    }
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{type},new MapperProxy<T>(sqlSession,type));
    }

    public  class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }

}
