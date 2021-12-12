package com.zmy.utils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.pojo.Book;
import com.zmy.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static Map<Page<Book>,List<Book>> pageBook(BookService bookService, int pageNo, int pageSize){
        Page<Book> books = PageHelper.startPage(pageNo, pageSize);
        List<Book> bookList = bookService.queryBookList();
        Map<Page<Book>,List<Book>> map=new HashMap<>();
        map.put(books,bookList);

        PageInfo<Book> bookPageInfo = books.toPageInfo();
        return map;
    }
}
