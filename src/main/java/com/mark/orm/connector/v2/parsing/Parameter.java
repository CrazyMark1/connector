package com.mark.orm.connector.v2.parsing;

import com.mark.orm.connector.v2.mapper.MapperParameters;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/6 14:52
 * @QQ: 85104982
 */
public class Parameter {
    private Class<?> type;
    private Object value;

    public Parameter(Class<?> type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
    public static MapperParameters convertArgsToParameters(Method method, Object[] args){
        MapperParameters mapperParameters=new MapperParameters();
        java.lang.reflect.Parameter[] parameters=method.getParameters();
        if (null!=parameters && parameters.length > 0){
            for (int i = 0; i <parameters.length ; i++) {
                Parameter parameter=new Parameter(parameters[i].getType(),args[i]);
                mapperParameters.addPapameter(parameter);
            }
            return mapperParameters;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(type, parameter.type) &&
                Objects.equals(value, parameter.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, value);
    }
}
