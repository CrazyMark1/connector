package com.mark.orm.connector.v2.config;

import com.mark.orm.connector.v2.executor.CacheExecutor;
import com.mark.orm.connector.v2.executor.Executor;
import com.mark.orm.connector.v2.executor.ExecutorFactory;
import com.mark.orm.connector.v2.executor.SimpleExecutor;
import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.parsing.AnnotationParser;
import com.mark.orm.connector.v2.parsing.PathParser;
import com.mark.orm.connector.v2.plugin.Interceptor;
import com.mark.orm.connector.v2.plugin.InterceptorChain;
import com.mark.orm.connector.v2.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:13
 * @QQ: 85104982
 */
public class Configuration {
    private String scanPath;
    private MapperRegistory mapperRegistory=new MapperRegistory();
    private final InterceptorChain interceptorChain = new InterceptorChain();

    public MapperRegistory getMapperRegistor(){
        return mapperRegistory;
    }

    public void build() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        initRegistory();
    }

    private void initRegistory() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        PathParser pathParser=new PathParser();
        List<String> list=pathParser.scannerClass(getScanPath());
        for (String s : list) {
            AnnotationParser.parserAndRegist(Class.forName(s.split(".class")[0]));
        }
    }
    public List<Interceptor> getInterceptors() {
        return interceptorChain.getInterceptors();
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptorChain.addInterceptor(interceptor);
    }

    public Executor newExecutor(String executorType) {
        executorType = executorType == null ? "CACHE" : "SIMPLE";
        Executor executor;
        if (executorType.equals("SIMPLE"))
            executor= ExecutorFactory.getExecutor(executorType,this);
        else
            executor=ExecutorFactory.getExecutor(executorType,this);
        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

    public void setScanPath(String scanPath){
        this.scanPath=scanPath;
    }

    public String getScanPath() {
        return scanPath;
    }

    public <T> T getMappper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistory.getMapper(type,sqlSession);
    }

}
