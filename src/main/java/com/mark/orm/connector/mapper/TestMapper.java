package com.mark.orm.connector.mapper;

import com.mark.orm.connector.bean.Test;
import com.mark.orm.connector.v2.annotations.Delete;
import com.mark.orm.connector.v2.annotations.Insert;
import com.mark.orm.connector.v2.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/2 19:14
 * @QQ: 85104982
 */
public interface TestMapper {
    @Select("select * from test where id =?")
    Test selectByPrimaryKey(Integer userId);

    @Insert("insert test(nums,name) values(?,?)")
    int insert(Integer nums,String name);

    @Delete("delete from test where id =?")
    int deleteByPrimaryKey(Integer id);

    @Select("select * from test")
    List<Test> selectAll();
}
