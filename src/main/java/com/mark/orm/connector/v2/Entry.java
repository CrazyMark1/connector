package com.mark.orm.connector.v2;

import com.mark.orm.connector.mapper.TestMapper;
import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.executor.SimpleExecutor;
import com.mark.orm.connector.v2.session.SqlSession;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/5 15:35
 * @QQ: 85104982
 */
public class Entry {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        SqlSession sqlSession=new SqlSession(configuration,new SimpleExecutor(configuration));
        TestMapper testMapper=sqlSession.getMapper(TestMapper.class);
        System.out.println(testMapper.selectByPrimaryKey(1));
    }
}
