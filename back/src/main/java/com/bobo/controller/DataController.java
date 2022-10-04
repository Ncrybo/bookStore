package com.bobo.controller;

import com.bobo.dao.DataMapper;
import com.bobo.entity.Book;
import com.bobo.entity.Category;
import com.bobo.entity.Result;
import com.bobo.service.LoginService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@RestController
public class DataController {
    @Autowired
    private DataMapper dataMapper;

    @PostMapping("/category")       //获取种类的名称
    public List<Category> getCategory() { return dataMapper.category(); }

    @PostMapping("/getCategoryBooks/{cid}")         //获取不同种类的书籍
    public List<Book> getCategoryBooks(@PathVariable Integer cid) {
        if (cid == 0)
            return dataMapper.getAllBooks();      //如果选中全部，则返回所有书籍
        return dataMapper.getCategoryBooks(cid);
    }
    @PostMapping("/getAllBooks" )
    public Result getAllBooks() {
        List<Book> books = dataMapper.getAllBooks();
        return Result.succeed(books);
    }

    @PostMapping("/searchBook/{name}")   //查找书
    public Result serachBook(@PathVariable String name) {
        name = '%'+name+'%';
        List<Book> book = dataMapper.searchBook(name);
        return Result.succeed(book);
    }
    @PostMapping("/getCategory/{id}")
    public Result getCategory(@PathVariable Integer id) {
        String name = dataMapper.getCategory(id);
        return Result.succeed(name);
    }

    @PostMapping("/getCNById/{id}")
    public String getCategoryNameById(@PathVariable Integer id) {
        return dataMapper.getCateroyNameById(id);
    }

}
