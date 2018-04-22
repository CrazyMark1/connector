package com.mark.orm.connector.v2.plugin;

import com.mark.orm.connector.v2.executor.Executor;
import com.mark.orm.connector.v2.mapper.MapperParameters;
import com.mark.orm.connector.v2.mapper.MapperRegistory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/22 16:09
 * @QQ: 85104982
 */
@Intercepts(
        @Signature(type = Executor.class,
        method = "query",
        args = {MapperRegistory.MapperData.class, MapperParameters.class}))
public class TestPlugin implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        MapperRegistory.MapperData mapperData= (MapperRegistory.MapperData) invocation.getArgs()[0];
        System.out.println(mapperData.getSql());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }
}
