package com.mark.orm.connector.v1;

import com.mark.orm.connector.bean.Test;
import com.mark.orm.connector.mapper.TestMapper;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 20:14
 * @QQ: 85104982
 */
public class Entry {
    public static void main(String[] args) {
        SqlSession sqlSession=new SqlSession(new Configuration(),new SimpleExecutor());
        TestMapper mapper=sqlSession.getMapper(TestMapper.class);
        Test test=mapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
