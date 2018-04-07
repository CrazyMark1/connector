package com.mark.orm.connector.v2.parsing;

import com.mark.orm.connector.v2.annotations.Delete;
import com.mark.orm.connector.v2.annotations.Insert;
import com.mark.orm.connector.v2.annotations.Select;
import com.mark.orm.connector.v2.annotations.Update;
import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.mapping.SqlCommandType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/7 17:17
 * @QQ: 85104982
 */
public class AnnotationParser {

    public static void parserAndRegist(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
         Method[] methods=clazz.getMethods();
         Class<?> returnType=null;
         //得到泛型里面类型
        for (Method method : methods) {
            Type type=method.getGenericReturnType();
            if (ParameterizedType.class.isAssignableFrom(type.getClass())){
                for (Type t:((ParameterizedType)type).getActualTypeArguments()) {
                    returnType= (Class<?>) t;
                }
            }
            else
                returnType= (Class<?>) type;

            String methodName=method.getName();
            String methodPath=method.getDeclaringClass().getName();
            Annotation[] annotations=method.getDeclaredAnnotations();
            String sql=null;
            Class<?> annotationType=null;
            for (Annotation annotation : annotations) {
                if (annotation instanceof Insert || annotation instanceof Select ||
                        annotation instanceof Update || annotation instanceof Delete){
                    sql= (String) annotation.getClass().getMethods()[0].invoke(annotation);
                    annotationType= annotation.annotationType();
                    break;
                }
            }
            MapperRegistory.registMapper(methodPath+"."+methodName,new MapperRegistory.MapperData(sql,returnType,transformType(annotationType)));
        }
    }

    private static SqlCommandType transformType(Class<?> clazz){
        if (clazz==Insert.class)
            return SqlCommandType.INSERT;
        else if (clazz==Update.class)
            return SqlCommandType.UPDATE;
        else if (clazz==Delete.class)
            return SqlCommandType.DELETE;
        else if (clazz==Select.class)
            return SqlCommandType.SELECT;
        else
            throw new RuntimeException("注解无法解析");
    }
}
