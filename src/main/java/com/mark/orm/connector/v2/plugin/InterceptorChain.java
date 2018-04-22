package com.mark.orm.connector.v2.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/22 15:55
 * @QQ: 85104982
 */
public class InterceptorChain {
    private final List<Interceptor> interceptors=new ArrayList<Interceptor>();

    public Object pluginAll(Object target){
        for (Interceptor interceptor : interceptors) {
            target=interceptor.plugin(target);
        }
        return  target;
    }
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }
}
