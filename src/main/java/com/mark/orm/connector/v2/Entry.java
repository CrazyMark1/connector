package com.mark.orm.connector.v2;

import com.mark.orm.connector.bean.Test;
import com.mark.orm.connector.mapper.TestMapper;
import com.mark.orm.connector.v2.config.Configuration;
import com.mark.orm.connector.v2.executor.ExecutorFactory;
import com.mark.orm.connector.v2.executor.SimpleExecutor;
import com.mark.orm.connector.v2.plugin.TestPlugin;
import com.mark.orm.connector.v2.session.SqlSession;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/5 15:35
 * @QQ: 85104982
 */
public class Entry {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Configuration configuration=new Configuration();
        configuration.setScanPath("com.mark.orm.connector.mapper");
        configuration.addInterceptor(new TestPlugin());
        configuration.build();
        SqlSession sqlSession=new SqlSession(configuration, configuration.newExecutor("CACHE"));
        TestMapper testMapper=sqlSession.getMapper(TestMapper.class);
//        System.out.println(testMapper.selectByPrimaryKey(1));
//        System.out.println(testMapper.selectByPrimaryKey(2));

//        System.out.println(testMapper.insert(100,"adasda"));
//        System.out.println(testMapper.deleteByPrimaryKey(2));
        List<Test> list=testMapper.selectByPrimaryKey2(1,4);
        for (Test test : list) {
            System.out.println(test);
        }
    }
}
