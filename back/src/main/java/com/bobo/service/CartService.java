package com.bobo.service;

import com.bobo.dao.CartMapper;
import com.bobo.entity.Book;
import com.bobo.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.*;

@Transactional
@Service
public class CartService {

    @Autowired
    CartMapper cartMapper;

    public List<Cart> searchCart(String uname) {
        return cartMapper.searchCart(uname);
    }

    public Integer addAmount(String uname, Integer gid) {          //增加
        if(cartMapper.searchGoods(uname,gid) > 0)
            return cartMapper.addAmount(uname, gid);
        else {
            return cartMapper.addAGoods(uname, gid);  //如果没有该商品的记录，则创建一个记录，并设置商品数量为1
        }
    }
    public Integer deamount(String uname, Integer gid) {        //减少
        return cartMapper.deamount(uname, gid);
    }

    public Integer delGoods(String uname, Integer gid) {       //删除商品
        return cartMapper.delGoods(uname, gid);
    }

    //查询购物车，返回购物车中的书籍信息
    public List<Book> getCartBooks(String uname) {
        List<Cart> cart = cartMapper.searchCart(uname);
        List<Book> cartBook = new LinkedList<>();
        Book book = new Book();
        Iterator<Cart> it = cart.iterator();
        while(it.hasNext()) {
            Cart item = it.next();
            book = cartMapper.findBookById(item.getGid());
            book.setPrice(item.getAmount() * book.getPrice());
            book.setAmount(item.getAmount());
            cartBook.add(book);
        }
        return cartBook;
    }
}
