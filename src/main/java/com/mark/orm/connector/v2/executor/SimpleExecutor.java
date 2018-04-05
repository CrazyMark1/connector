package com.mark.orm.connector.v2.executor;

import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.statment.StatmentHandler;

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



    public <T> T query(MapperRegistory.MapperData mapperData, Object parameter) {
        StatmentHandler handler=new StatmentHandler(configuration);
        return handler.query(mapperData,parameter);
    }
}
