package com.mark.orm.connector.v2.executor;

import com.mark.orm.connector.v2.mapper.MapperRegistory; /**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/4 10:13
 * @QQ: 85104982
 */
public interface Executor {
    <T> T query(MapperRegistory.MapperData mapperData, Object parameter);
}
