package com.mark.orm.connector.v2;

import com.mark.orm.connector.bean.Test;
import com.mark.orm.connector.mapper.TestMapper;
import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.executor.SimpleExecutor;
import com.mark.orm.connector.v2.session.SqlSession;

import java.util.List;

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
//        System.out.println(testMapper.selectByPrimaryKey(1));
//        System.out.println(testMapper.insert(100,"adasda"));
//        System.out.println(testMapper.deleteByPrimaryKey(2));
        List<Test> list=testMapper.selectAll();
        for (Test test : list) {
            System.out.println(test);
        }
    }
}
