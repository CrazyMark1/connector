package com.mark.orm.connector.v2.mapper;

import com.mark.orm.connector.v2.parsing.Parameter;

import java.util.*;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/6 13:26
 * @QQ: 85104982
 */
public class MapperParameters{
    private final List<Parameter> parameters=new ArrayList<Parameter>();

    public void addPapameter(Parameter parameter){
        parameters.add(parameter);
    }

    public List<Parameter> getPapameters() {
        return parameters;
    }

    public int size(){
        return parameters.size();
    }

}
