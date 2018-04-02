package com.mark.orm.connector.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:13
 * @QQ: 85104982
 */
public class MapperProxy implements InvocationHandler{
    private SqlSession sqlSession;
    public MapperProxy(SqlSession sqlSession){
        this.sqlSession=sqlSession;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass().getName().equals(Configuration.TestMapperXml.NAMESPACE)){
            String sql=Configuration.TestMapperXml.METHODSQLMAPPING.get(method.getName());
            return sqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
