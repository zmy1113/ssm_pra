package com.zmy.utils;

import com.github.pagehelper.Page;
import com.zmy.pojo.Book;
import com.zmy.service.BookService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class PageUtilTest extends TestCase {
    ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
    BookService bookService = (BookService) context.getBean("bookService");
    @Test
    public void testPage(){
        Map<Page<Book>, List<Book>> pageListMap = PageUtil.pageBook(bookService, 1, 4);
        for (Map.Entry<Page<Book>, List<Book>> pageListEntry : pageListMap.entrySet()) {
            List<Book> value = pageListEntry.getValue();
            Page<Book> key = pageListEntry.getKey();
            for (Book book : value) {
                System.out.println(book);
            }
            System.out.println(key.getPageSize());
        }
    }

}