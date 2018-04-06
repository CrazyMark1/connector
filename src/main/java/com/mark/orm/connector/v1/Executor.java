package com.mark.orm.connector.v1;

import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:12
 * @QQ: 85104982
 */
public interface Executor {
    <T> T query(String statement, String parameter);
}
