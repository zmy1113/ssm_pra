package com.zmy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zmy.dao.BookDao;
import com.zmy.pojo.Book;
import com.zmy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired(required = false)
    @Qualifier("bookDao")
    private BookDao bookDao;

    @Override
    public List<Book> queryBookList() {
        List<Book> books1 = bookDao.queryBookList();
        return books1;
    }

    @Override
    public List<Book> pageBooksByPrice(Integer startPrice, Integer endPrice) {
        List<Book> bookList = bookDao.queryBookByPrice(startPrice, endPrice);
        return bookList;
    }

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public void updateBook(Book book,Integer id) {
        bookDao.updateBook(book, id);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }
}
