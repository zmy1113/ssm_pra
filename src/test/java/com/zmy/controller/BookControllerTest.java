package com.zmy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.dao.CartDao;
import com.zmy.pojo.Book;
import com.zmy.pojo.CartItem;
import com.zmy.pojo.Order;
import com.zmy.service.BookService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;


public class BookControllerTest extends TestCase {

    @Test
    public void testController() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        Book book=new Book();
        book.setName("时间简史");
        book.setAuthor("霍金");
        book.setPrice(new BigDecimal(80));
        book.setSales(10);
        book.setStock(100);
        Page<Book> bookPage = PageHelper.startPage(1, 4);
        bookService.queryBookList();
        PageInfo<Book> bookPageInfo = bookPage.toPageInfo();
        System.out.println("================="+bookPage.getEndRow()+"================");
        bookService.addBook(book);

    }
    @Test
    public void testC(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CartDao cartDao = (CartDao) context.getBean("cartDao");

        CartItem cartItem=new CartItem();
        cartItem.setName("东邪西毒");
        cartItem.setCount(2);
        cartItem.setPrice(new BigDecimal(70));
        cartItem.setTotalPrice(new BigDecimal(140));

        cartDao.saveCartItem(cartItem,1);


        Order order=new Order();
        order.setOrderId("11233");
        System.out.println(order.getOrderId());
        order.setOrderId("124");
        System.out.println(order.getOrderId());
    }
}