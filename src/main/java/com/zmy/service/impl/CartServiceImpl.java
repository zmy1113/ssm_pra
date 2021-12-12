package com.zmy.service.impl;

import com.zmy.dao.CartDao;
import com.zmy.pojo.Book;
import com.zmy.pojo.CartItem;
import com.zmy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    @Override
    public List<CartItem> queryCartByUserId(Integer userId) {
        return cartDao.queryCartByUserId(userId);
    }

    @Override
    public void saveCartItem(Book book,Integer userId) {
        CartItem cartItem=new CartItem();
        if(cartDao.existCartItem(book.getName(),userId)!=null){
            cartDao.updateCount(1,book.getName(),userId);
        }else {
            cartItem.setName(book.getName());
            cartItem.setPrice(book.getPrice());
            cartItem.setCount(1);
            cartItem.setTotalPrice(book.getPrice());

            cartDao.saveCartItem(cartItem,userId);
        }

    }

    @Override
    public void clear(Integer userId) {
        cartDao.clear(userId);
    }

    @Override
    public void deleteById(Integer cartId) {
        cartDao.deleteById(cartId);
    }

    @Override
    public void updateCount(Integer count,String name,Integer userId) {
        cartDao.updateCountByUpdate(count,name,userId);
    }
}
