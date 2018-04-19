package com.mark.orm.connector.v2.executor;

import com.mark.orm.connector.v2.cache.CacheKey;
import com.mark.orm.connector.v2.mapper.MapperParameters;
import com.mark.orm.connector.v2.mapper.MapperRegistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/19 15:53
 * @QQ: 85104982
 */
public class CacheExecutor implements Executor{
    private SimpleExecutor delegate;
    private Map<CacheKey,List> localCache = new HashMap<CacheKey,List>();

    public CacheExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <E> List<E> query(MapperRegistory.MapperData mapperData, MapperParameters mapperPapameters) {
        Object[] objs={mapperData.getSql(),mapperPapameters.getPapameters()};
        CacheKey key=new CacheKey(objs);
        List<E> result=localCache.get(key);
        if (result!=null){
            System.out.println("命中缓存");
            return result;
        }
        result= (List<E>) delegate.query(mapperData,mapperPapameters);
        localCache.put(key,result);
        return result;
    }

    @Override
    public int update(MapperRegistory.MapperData mapperData, MapperParameters mapperPapameters) {
        return delegate.update(mapperData,mapperPapameters);
    }
}
