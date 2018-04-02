package com.mark.orm.connector.v1;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:11
 * @QQ: 85104982
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public  SqlSession(Configuration configuration,Executor executor){
        this.configuration=configuration;
        this.executor=executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return  configuration.getMapper(clazz,this);
    }

    public <T> T selectOne(String statement,String parameter){
        return executor.query(statement,parameter);
    }

}
