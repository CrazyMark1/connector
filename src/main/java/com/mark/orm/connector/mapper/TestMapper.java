package com.mark.orm.connector.mapper;

import com.mark.orm.connector.bean.Test;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:14
 * @QQ: 85104982
 */
public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}