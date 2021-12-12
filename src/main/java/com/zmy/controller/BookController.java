package com.zmy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.pojo.Book;
import com.zmy.service.BookService;
import com.zmy.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;


    @RequestMapping("/indexBook/{pageNo}")
    public String indexBook(
            @PathVariable Integer pageNo,
            HttpServletRequest req, HttpServletResponse rep){
        if(pageNo<=0){
            pageNo=1;
        }
        Page<Book> books = PageHelper.startPage(pageNo, 4);
        List<Book> bookList = bookService.queryBookList();
        PageInfo<Book> bookPageInfo = books.toPageInfo();
        req.setAttribute("books",bookPageInfo);
        req.setAttribute("bookList",bookList);
        req.setAttribute("url","indexBook/");
        return "client/index";
    }
    /*@RequestMapping("/test/{pageNo}")
    public String test(
            @PathVariable Integer pageNo,
            HttpServletRequest req, HttpServletResponse rep){
        if(pageNo<=0){
            pageNo=1;
        }
        Page<Book> books = PageHelper.startPage(pageNo, 4);
        List<Book> bookList = bookService.queryBookList();
        PageInfo<Book> bookPageInfo = books.toPageInfo();
        req.setAttribute("books",bookPageInfo);
        req.setAttribute("bookList",bookList);
        return "user/test";
    }*/

    @GetMapping("/pageByPrice/{startPrice}/{endPrice}/{pageNo}")
    public String pageByPrice(HttpServletRequest req,
                              @PathVariable Integer startPrice,
                              @PathVariable Integer endPrice,
                              @PathVariable Integer pageNo){
        if (pageNo<=0){
            pageNo=1;
        }
        Page<Book> books = PageHelper.startPage(pageNo, 4);
        List<Book> bookList = bookService.pageBooksByPrice(startPrice, endPrice);
        PageInfo<Book> bookPageInfo = books.toPageInfo();
        req.setAttribute("bookList",bookList);
        req.setAttribute("books",bookPageInfo);
        req.setAttribute("url","pageByPrice/"+startPrice+"/"+endPrice+"/");
        return "client/index";
    }
}
