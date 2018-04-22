package com.mark.orm.connector.v2.plugin;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/22 14:46
 * @QQ: 85104982
 */
public interface Interceptor {
    Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException;
    Object plugin(Object target);
}
