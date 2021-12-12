package com.zmy.service;

import com.zmy.pojo.Book;
import com.zmy.pojo.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> queryCartByUserId(Integer userId);

    void saveCartItem(Book book,Integer userId);

    void clear(Integer userId);

    void deleteById(Integer cartId);

    void updateCount(Integer count,String name,Integer userId);
}
