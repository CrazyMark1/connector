package com.mark.orm.connector.v2.config;

import com.mark.orm.connector.v2.mapper.MapperRegistory;
import com.mark.orm.connector.v2.session.SqlSession;
/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:13
 * @QQ: 85104982
 */
public class Configuration {
    private MapperRegistory mapperRegistor=new MapperRegistory();

    public MapperRegistory getMapperRegistor(){
        return mapperRegistor;
    }

    public <T> T getMappper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistor.getMapper(type,sqlSession);
    }
}
