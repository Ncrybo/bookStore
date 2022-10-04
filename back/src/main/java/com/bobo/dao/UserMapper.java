package com.bobo.dao;

import com.bobo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{name}")
    public User getByName(String name);
}
