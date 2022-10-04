package com.bobo.controller;

import com.bobo.dao.CartMapper;
import com.bobo.entity.Book;
import com.bobo.entity.Result;
import com.bobo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    HttpServletRequest req;

    @PostMapping("/searchCart")
    public Result searchCart() {
        String name = (String) req.getAttribute("username");
        List<Book> books = cartService.getCartBooks(name);
        return  Result.succeed(books);
    }

    @PostMapping("/addAmount/{gid}")
    public Result addAmount(@PathVariable("gid") Integer gid) {   //增加一本书
        String name = (String) req.getAttribute("username");
        if(cartService.addAmount(name, gid) > 0) {
            Integer amount = cartMapper.amount(name, gid);
            return Result.succeed("添加成功",amount);
        }

        else
            return Result.fail("添加失败！");
    }

    @PostMapping("deAmount/{gid}")
    public Result deAmount(@PathVariable("gid")Integer gid) {
        String name = (String) req.getAttribute("username");
        if(cartService.deamount(name, gid) > 0) {
            Integer amount = cartMapper.amount(name, gid);
            return Result.succeed("删除成功！",amount);
        }
        else
            return Result.fail("删除失败!", null);
    }

    @PostMapping("delGoods/{gid}")
    public Result delGoods( @PathVariable("gid")Integer gid) {
        String name = (String) req.getAttribute("username");
        if(cartService.delGoods(name, gid) > 0) {
            Integer amount = cartMapper.amount(name, gid);
            return Result.succeed("删除整个商品",amount);
        }

        else
            return Result.fail("删除商品失败",null);
    }

    @PostMapping("/amount/{uname}/{gid}")
    public Result amount(@PathVariable("uname")String uname, @PathVariable("gid")Integer gid) {
        Integer t = cartMapper.amount(uname, gid);
        return Result.succeed(t);
    }
}
