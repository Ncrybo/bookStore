package com.bobo.dao;

import com.bobo.entity.Book;
import com.bobo.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    //创建一个商品记录
    @Insert("INSERT INTO cart(uname,gid,amount) values(#{uname}, #{gid}, 1)")
    public Integer addAGoods(String uname, Integer gid);

    //商品加一
    @Update("update cart set amount = amount + 1 where uname = #{uname} and gid = #{gid}")
    public Integer addAmount(@Param("uname")String uname, @Param("gid")Integer gid);

    //商品数量减一，数据库有触发器，当商品数量为0时，自动删除记录
    @Update("update cart set amount = amount - 1 where uname = #{uname} and gid = #{gid}")
    public Integer deamount(@Param("uname")String uname, @Param("gid")Integer gid);

    //删除指定商品
    @Update("update cart set amount = 0 where uname = #{uname} and gid = #{gid}")
    public Integer delGoods(@Param("uname") String uname, @Param("gid") Integer gid);

    //查询是否存在商品记录
    @Select("select count(*) from cart where uname = #{uname} and gid = #{gid}")
    public Integer searchGoods(@Param("uname")String uname, @Param("gid")Integer gid);

    //查询用户购物车
    @Select("select gid, amount from cart where uname = #{uname}")
    public List<Cart> searchCart(String uname);

    @Select("select amount from cart where uname = #{uname} and gid = #{gid}")
    public Integer amount(@Param("uname")String uname, @Param("gid")Integer gid);

    @Select("select * from book where id = #{gid}")
    public Book findBookById(Integer gid);

}
