package com.bobo.dao;

import com.bobo.entity.Book;
import com.bobo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataMapper {

    @Select("select id,name from category")
    public List<Category> category();

    @Select("select name from category where id = #{id}")
    public String getCategory(Integer id);

    @Select("select * from book where category = #{cid}")
    public List<Book> getCategoryBooks(Integer cid);

    @Select("select * from book")
    public List<Book> getAllBooks();

    @Select("select * from book where name like #{name}")
    public List<Book> searchBook(String name);

    @Select("select name from category where id = #{id}")
    public String getCateroyNameById(Integer id);
}
