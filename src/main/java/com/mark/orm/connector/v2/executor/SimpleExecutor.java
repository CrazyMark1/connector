package com.mark.orm.connector.v2.executor;

import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.mapper.MapperParameters;
import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.statment.StatementHandler;

import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:13
 * @QQ: 85104982
 */
public class SimpleExecutor implements Executor{

    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <E> List<E> query(MapperRegistory.MapperData mapperData, MapperParameters mapperPapameters) {
        StatementHandler handler=new StatementHandler(configuration);
        return handler.query(mapperData,mapperPapameters);
    }
    @Override
    public int update(MapperRegistory.MapperData mapperData, MapperParameters papameters) {
        StatementHandler handler=new StatementHandler(configuration);
        return handler.update(mapperData,papameters);
    }
}
