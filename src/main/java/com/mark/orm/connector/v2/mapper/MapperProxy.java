package com.mark.orm.connector.v2.mapper;

import com.mark.orm.connector.v2.parsing.Parameter;
import com.mark.orm.connector.v2.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/5 14:07
 * @QQ: 85104982
 */
public class MapperProxy<T> implements InvocationHandler{
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistory.MapperData mapperData=sqlSession.getConfiguration()
                .getMapperRegistor()
                .getMapperData(method.getDeclaringClass().getName()+"."+method.getName());
        if (null != mapperData){
            MapperParameters mapperParameters= Parameter.convertArgsToParameters(method,args);
            switch (mapperData.getType()){
                case DELETE:
                    return sqlSession.delete(mapperData,mapperParameters);
                case INSERT:
                    return sqlSession.insert(mapperData,mapperParameters);
                case SELECT:
                    List list=sqlSession.selectList(mapperData,mapperParameters);
                    if (list.size()>0){
                        if (method.getReturnType()!=List.class)
                            return list.get(0);
                        return list;
                    }
                    else
                        return null;
                case UPDATE:
                    return sqlSession.update(mapperData,mapperParameters);
            }
        }
        return method.invoke(proxy, args);
    }
}
