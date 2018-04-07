package com.mark.orm.connector.v2;

import com.mark.orm.connector.mapper.TestMapper;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/7 16:54
 * @QQ: 85104982
 */
public class Test {

    public static void main(String[] args) throws NoSuchFieldException {
        Class clazz= TestMapper.class;
        Method method=clazz.getMethods()[1];
        Type type=method.getGenericReturnType();
        if (ParameterizedType.class.isAssignableFrom(type.getClass())){
            for (Type t:((ParameterizedType)type).getActualTypeArguments()) {
                System.out.println(t);
            }
        }
        else
            System.out.println("不是");


    }
}
