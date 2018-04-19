package com.mark.orm.connector.v2.executor;

import com.mark.orm.connector.v2.config.Configuration;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/19 15:44
 * @QQ: 85104982
 */
public class ExecutorFactory {
    public static final String SIMPLE="SIMPLE";
    public static final String CACHE="CACHE";

    public static Executor getExecutor(String key , Configuration configuration){
        if (SIMPLE.equalsIgnoreCase(key)){
            return new SimpleExecutor(configuration);
        }
        if (CACHE.equalsIgnoreCase(key)){
            return new CacheExecutor(new SimpleExecutor(configuration));
        }
        throw new RuntimeException("No excutor found!");
    }

}
