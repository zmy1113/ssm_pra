package com.zmy.controller;

import com.zmy.pojo.CartItem;
import com.zmy.pojo.Order;
import com.zmy.pojo.OrderItem;
import com.zmy.pojo.User;
import com.zmy.service.CartService;
import com.zmy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @RequestMapping("manager/myOrder/{userId}")
    public String MyOrder(@PathVariable Integer userId,
                          HttpServletRequest req){
        List<Order> orders = orderService.queryOrderList(userId);
        req.getSession().setAttribute("orders",orders);
        return "order/order";
    }

    @RequestMapping("manager/orderDetails/{orderId}")
    public String OrderDetails(@PathVariable String orderId,
                               HttpServletRequest req){
        List<OrderItem> orderItem = orderService.queryOrderDetailsById(orderId);
        req.getSession().setAttribute("orderItem",orderItem);

        return "order/order_details";
    }

    @RequestMapping("manager/receive/{orderId}")
    public String receive(@PathVariable String orderId,
                          HttpServletRequest req,
                          HttpServletResponse resp){
        Integer receive = orderService.receive(orderId);
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.queryOrderList(user.getId());
        if(receive!=0){
            req.getSession().setAttribute("orders",orders);
        }else {
            req.getSession().setAttribute("orders",null);
        }
        return "order/order";
    }

    @RequestMapping("/createOrder")
    public String createOrder(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("user");
        List<CartItem> cartItems = cartService.queryCartByUserId(user.getId());

        orderService.createOrder(cartItems,user.getId());
        cartService.clear(user.getId());
        return "redirect:/indexBook/1";
    }
}
