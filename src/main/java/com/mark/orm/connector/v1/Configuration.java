package com.mark.orm.connector.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:11
 * @QQ: 85104982
 */
public class Configuration {
    public <T> T getMapper(Class<T> clazz,SqlSession sqlSession){
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{clazz},new MapperProxy(sqlSession));
    }

    static class TestMapperXml{
        public static final String NAMESPACE="com.mark.orm.connector.v1.TestMapper";
        public static final Map<String,String> METHODSQLMAPPING=new HashMap<String, String>();

        static{
            METHODSQLMAPPING.put("selectByPrimaryKey","select * from test where id =%d");
        }
    }
}
