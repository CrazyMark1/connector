package com.mark.orm.connector.v2.mapper;

import com.mark.orm.connector.v2.mapping.SqlCommandType;
import com.mark.orm.connector.v2.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:28
 * @QQ: 85104982
 */
public class MapperRegistory {
    private static final Map<String,MapperData> METHODSQLMAPPING=new ConcurrentHashMap<String, MapperData>();

    public MapperRegistory() {
//        METHODSQLMAPPING.put("com.mark.orm.connector.mapper.TestMapper.selectByPrimaryKey",
//                new MapperData("select * from test where id =?",Test.class,SqlCommandType.SELECT));
//        METHODSQLMAPPING.put("com.mark.orm.connector.mapper.TestMapper.insert",
//                new MapperData("insert test(nums,name) values(?,?)",Integer.class,SqlCommandType.INSERT));
//        METHODSQLMAPPING.put("com.mark.orm.connector.mapper.TestMapper.deleteByPrimaryKey",
//                new MapperData("delete from test where id =?",Integer.class,SqlCommandType.DELETE));
//        METHODSQLMAPPING.put("com.mark.orm.connector.mapper.TestMapper.selectAll",
//                new MapperData("select * from test where id>1", Test.class,SqlCommandType.SELECT));
    }

    public static void registMapper(String nameSpaceMethod,MapperData mapperData){
        METHODSQLMAPPING.put(nameSpaceMethod,mapperData);
    }

    public <T>MapperData getMapperData(String nameSpaceMethod){
        return METHODSQLMAPPING.get(nameSpaceMethod);
    }
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{type},new MapperProxy<T>(sqlSession,type));
    }

    public  static class MapperData<T>{
        private final String sql;
        private final Class<T> returnType;
        private final SqlCommandType type;

        public MapperData(String sql, Class<T> returnType, SqlCommandType type) {
            this.sql = sql;
            this.returnType = returnType;
            this.type=type;
        }

        public String getSql() {
            return sql;
        }

        public Class<T> getReturnType() {
            return returnType;
        }

        public SqlCommandType getType() {
            return type;
        }

    }

}
