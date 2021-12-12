package com.zmy.controller;

import com.alibaba.fastjson.JSON;
import com.zmy.pojo.Book;
import com.zmy.pojo.CartItem;
import com.zmy.pojo.User;
import com.zmy.service.BookService;
import com.zmy.service.CartService;
import com.zmy.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/ToCart/{userId}")
    public String toCart(HttpServletRequest req, @PathVariable Integer userId) {
        int totalCount = 0;
        BigDecimal totalCountPrice = new BigDecimal(0);
        List<CartItem> cartItems = cartService.queryCartByUserId(userId);
        for (CartItem cartItem : cartItems) {
            //计算总商品数量和总价
            totalCount += cartItem.getCount();
            totalCountPrice = totalCountPrice.add(cartItem.getTotalPrice());
        }
        req.getSession().setAttribute("totalCount", totalCount);
        req.getSession().setAttribute("totalCountPrice", totalCountPrice);
        req.getSession().setAttribute("cart", cartItems);
        return "cart/cart";
    }

    @RequestMapping("/addToCart")
    public void addToCart(HttpServletRequest req, HttpServletResponse response) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer bookId = WebUtil.parameterInt(req.getParameter("bookId"), 1);


        Book book = bookService.queryBookById(bookId);
        cartService.saveCartItem(book, user.getId());

        book.setStock(book.getStock()-1);
        book.setSales(book.getSales()+1);

        bookService.updateBook(book,bookId);

        int totalCount = 0;
        List<CartItem> cartItems = cartService.queryCartByUserId(user.getId());
        for (CartItem cartItem : cartItems) {
            //计算总商品数量
            totalCount += cartItem.getCount();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("lastName", book.getName());
        map.put("totalCount", totalCount);
        req.getSession().setAttribute("lastName", book.getName());
        response.getWriter().write(JSON.toJSONString(map));
    }

    @RequestMapping("/cart/clear")
    public String clearCart(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        cartService.clear(user.getId());

        return "forward:/ToCart/" + user.getId();
    }

    @RequestMapping("/cart/delete/{cartId}")
    public String deleteCart(@PathVariable Integer cartId,
                             HttpServletRequest req) {
        cartService.deleteById(cartId);
        User user = (User) req.getSession().getAttribute("user");
        return "redirect:/ToCart/"+user.getId();
    }

    @RequestMapping("/updateCount")
    public String updateCount(HttpServletRequest req){
        Integer count = WebUtil.parameterInt(req.getParameter("count"),1);
        String name = req.getParameter("name");
        User user = (User) req.getSession().getAttribute("user");
        cartService.updateCount(count,name,user.getId());

        return "redirect:/ToCart/"+user.getId();
    }

}
