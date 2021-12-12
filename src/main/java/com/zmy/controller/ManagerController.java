package com.zmy.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.pojo.Book;
import com.zmy.pojo.Order;
import com.zmy.service.BookService;
import com.zmy.service.OrderService;
import com.zmy.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/ToManager")
    public String ToManager() {
        return "manager/manager";
    }

    @RequestMapping("/manager/bookManager/{pageNum}")
    public String bookManager(HttpServletRequest req, @PathVariable Integer pageNum) {
        Page<Book> bookPage = PageHelper.startPage(pageNum, 4);
        List<Book> bookList = bookService.queryBookList();
        PageInfo<Book> bookPageInfo = bookPage.toPageInfo();
        req.setAttribute("bookList", bookList);
        req.setAttribute("books", bookPageInfo);
        req.setAttribute("url", "manager/bookManager/");

        return "manager/book_manager";
    }

    @RequestMapping("/manager/orderManager/{pageNo}")
    public String orderManager(@PathVariable Integer pageNo,
                               HttpServletRequest req) {
        Page<Order> orderPage = PageHelper.startPage(pageNo, 5);
        List<Order> orders = orderService.queryAllOrders();

        PageInfo<Order> orderPageInfo = orderPage.toPageInfo();

        req.getSession().setAttribute("orderPageInfo", orderPageInfo);
        req.getSession().setAttribute("orderList", orders);
        req.getSession().setAttribute("url", "manager/orderManager/");
        return "manager/order_manager";
    }

    @RequestMapping("/ToAdd/{pageNo}")
    public String ToAdd(@PathVariable Integer pageNo,
                        HttpServletRequest req) {
        req.setAttribute("pageNo", pageNo);
        return "manager/book_edit";
    }

    @RequestMapping("/add")
    public String addBook(HttpServletRequest req, Book book) {
        Integer pageNum = WebUtil.parameterInt(req.getParameter("pageNo"), 1);

        //保存图书信息
        bookService.addBook(book);
        //分页插件
        Page<Book> bookPage = PageHelper.startPage(pageNum, 4);
        //查询所有图书
        List<Book> bookList = bookService.queryBookList();

        PageInfo<Book> bookPageInfo = bookPage.toPageInfo();
        //获取最后一页
        int lastPage = bookPageInfo.getNavigateLastPage();
        //设置最后一页，防止新增图书时，多了一页，前台页面还停留在以前的地方
        if (pageNum != lastPage) {
            bookPageInfo.setPageNum(lastPage);
        }
        req.setAttribute("bookList", bookList);
        req.setAttribute("books", bookPageInfo);
        return "forward:/manager/bookManager/" + bookPageInfo.getPageNum();
    }

    @RequestMapping("/ToUpdate/{id}/{pageNo}")
    public String update(HttpServletRequest req,
                         @PathVariable Integer id,
                         @PathVariable Integer pageNo) {
        Book book = bookService.queryBookById(id);
        req.setAttribute("pageNo", pageNo);
        req.setAttribute("book", book);
        return "manager/book_edit";
    }

    @RequestMapping("/update")
    public String updateBook(Book book, HttpServletRequest req) {
        Integer pageNum = WebUtil.parameterInt(req.getParameter("pageNo"), 1);
        Integer id = WebUtil.parameterInt(req.getParameter("id"), 30);

        bookService.updateBook(book, id);
        //分页插件
        Page<Book> bookPage = PageHelper.startPage(pageNum, 4);
        //查询所有图书
        List<Book> bookList = bookService.queryBookList();

        PageInfo<Book> bookPageInfo = bookPage.toPageInfo();
        //获取最后一页
        int lastPage = bookPageInfo.getNavigateLastPage();
        //设置最后一页，防止新增图书时，多了一页，前台页面还停留在以前的地方
        if (pageNum != lastPage) {
            bookPageInfo.setPageNum(lastPage);
        }
        req.setAttribute("bookList", bookList);
        req.setAttribute("books", bookPageInfo);
        return "forward:/manager/bookManager/" + bookPageInfo.getPageNum();
    }

    @RequestMapping("/manager/delete/{id}/{pageNum}")
    public String delete(@PathVariable Integer id,
                         @PathVariable Integer pageNum,
                         HttpServletRequest req) {
        bookService.deleteBookById(id);
        //分页插件
        Page<Book> bookPage = PageHelper.startPage(pageNum, 4);
        //查询所有图书
        bookService.queryBookList();

        PageInfo<Book> bookPageInfo = bookPage.toPageInfo();
        //获取最后一页
        int lastPage = bookPageInfo.getNavigateLastPage();
        //设置最后一页，防止新增图书时，多了一页，前台页面还停留在以前的地方
        if (pageNum != lastPage) {
            bookPageInfo.setPageNum(lastPage);
        }
        return "forward:/manager/bookManager/" + bookPageInfo.getPageNum();
    }

    @RequestMapping("/sendOrder/{orderId}/{pageNo}")
    public String sendOrder(@PathVariable String orderId,
                            @PathVariable Integer pageNo) {
        orderService.sendOrder(orderId);
        return "redirect:/manager/orderManager/"+pageNo;
    }
}
