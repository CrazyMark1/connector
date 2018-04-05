package com.mark.orm.connector.v2.session;

import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.executor.Executor;
import com.mark.orm.connector.v2.mapper.MapperRegistory;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:11
 * @QQ: 85104982
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration,Executor executor){
        this.configuration=configuration;
        this.executor=executor;
    }
    public Configuration getConfiguration(){
        return configuration;
    }
    public <T> T selectOne(MapperRegistory.MapperData mapperData,Object parameter){

        return  executor.query(mapperData,parameter);
    }

    public <T> T getMapper(Class<T> type){
        return configuration.getMappper(type,this);
    }

}
