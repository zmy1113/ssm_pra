package com.zmy.service;

import com.zmy.pojo.Book;
import java.util.List;


public interface BookService {
    List<Book> queryBookList();

    List<Book> pageBooksByPrice(Integer startPrice, Integer endPrice);

    void addBook(Book book);

    Book queryBookById(Integer id);

    void updateBook(Book book,Integer id);

    void deleteBookById(Integer id);
}
