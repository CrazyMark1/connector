package com.mark.orm.connector.v2.session;

import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.executor.Executor;
import com.mark.orm.connector.v2.mapper.MapperParameters;
import com.mark.orm.connector.v2.mapper.MapperRegistory;

import java.util.List;

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
    public <E> E selectOne(MapperRegistory.MapperData mapperData, MapperParameters papameters){
        List<E> list =selectList(mapperData,papameters);
        if (null != list && list.size()>0)
            return list.get(0);
        return  null;
    }

    public <T> T selectOne(MapperRegistory.MapperData mapperData){
        return selectOne(mapperData,null);
    }

    public <E> List<E> selectList(MapperRegistory.MapperData mapperData, MapperParameters papameters) {
        return executor.query(mapperData,papameters);
    }
    public <E> List<E> selectList(MapperRegistory.MapperData mapperData) {
        return this.selectList(mapperData, null);
    }

    public int insert(MapperRegistory.MapperData mapperData, MapperParameters papameters){
        return  executor.update(mapperData,papameters);
    }

    public int insert(MapperRegistory.MapperData mapperData){
        return insert(mapperData,null);
    }

    public int update(MapperRegistory.MapperData mapperData, MapperParameters papameters){
        return  executor.update(mapperData,papameters);
    }

    public int update(MapperRegistory.MapperData mapperData){
        return update(mapperData,null);
    }

    public int delete(MapperRegistory.MapperData mapperData, MapperParameters papameters){
        return  executor.update(mapperData,papameters);
    }

    public int delete(MapperRegistory.MapperData mapperData){
        return delete(mapperData,null);
    }

    public <T> T getMapper(Class<T> type){
        return configuration.getMappper(type,this);
    }

}
