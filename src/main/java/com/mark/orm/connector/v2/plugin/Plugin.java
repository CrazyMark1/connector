package com.mark.orm.connector.v2.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/22 14:51
 * @QQ: 85104982
 */
public class Plugin implements InvocationHandler {
    private Object target;
    private Interceptor interceptor;
    private Map<Class<?>,Set<Method>> signamtureMap;

    public Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signamtureMap) {
        this.target = target;
        this.interceptor = interceptor;
        this.signamtureMap = signamtureMap;
    }
    
    public static Object wrap(Object target,Interceptor interceptor){
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type=target.getClass();
        Class<?>[] interfaces=getAllInterfaces(type,signatureMap);
        if (interfaces.length>0){
            return Proxy.newProxyInstance(
                    type.getClassLoader(),
                    interfaces,
                    new Plugin(target,interceptor,signatureMap));
        }
        return target;
    }

    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        Set<Class<?>> interfaces=new HashSet<Class<?>>();
        while (type!=null){
            for (Class<?> c:type.getInterfaces()) {
                if (signatureMap.containsKey(c)){
                    interfaces.add(c);
                }
            }
            type=type.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }


    private static Map<Class<?>,Set<Method>> getSignatureMap(Interceptor interceptor) {
        Intercepts interceptsAnnotation=interceptor.getClass().getAnnotation(Intercepts.class);
        if (interceptsAnnotation == null){
            throw new RuntimeException("No Such Interceptor");
        }
        Signature[] sigs=interceptsAnnotation.value();
        Map<Class<?>,Set<Method>> signamtureMap=new HashMap<Class<?>, Set<Method>>();
        for (Signature sig : sigs) {
            Set<Method> methods=signamtureMap.get(sig.type());
            if (methods==null){
                methods=new HashSet<Method>();
                signamtureMap.put(sig.type(),methods);
            }
            try {
                Method method=sig.type().getMethod(sig.method(),sig.args());
                methods.add(method);
            }catch (NoSuchMethodException e){
                throw new RuntimeException("Not found method");
            }

        }
        return signamtureMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods=signamtureMap.get(method.getDeclaringClass());
        if (methods != null && methods.contains(method)){
            return interceptor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,args);
    }
}
