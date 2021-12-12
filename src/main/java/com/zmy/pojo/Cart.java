package com.zmy.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    //    private Integer totalCount;总商品数量
//    private BigDecimal totalCountPrice;总商品金额
    private Map<Integer,CartItem> item=new LinkedHashMap<Integer,CartItem>();

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItems(CartItem cartItem){
        //判断有无该商品

        CartItem items = item.get(cartItem.getId());
        if(items==null){
            //如果没有则添加该商品项
            item.put(cartItem.getId(),cartItem);
        }else{
            //如果有则数量加1,并且更新总商品金额
            items.setCount(items.getCount()+1);
            items.setTotalPrice(items.getPrice().multiply(new BigDecimal(items.getCount())));
        }

    }

    /**
     * 更新商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        //检查是否有该商品，如果有则修改数量,且更新总金额
        CartItem items = item.get(id);
        if(items!=null){
            items.setCount(count);
            items.setTotalPrice((items.getPrice().multiply(new BigDecimal(items.getCount()))));
        }
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        item.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        item.clear();
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        for(Map.Entry<Integer,CartItem>entry:item.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalCountPrice() {
        BigDecimal totalCountPrice=new BigDecimal(0);
        for(Map.Entry<Integer,CartItem>entry:item.entrySet()){
            totalCountPrice=totalCountPrice.add(entry.getValue().getTotalPrice());
        }
        return totalCountPrice;
    }


    public Map<Integer, CartItem> getItem() {
        return item;
    }

    public void setItem(Map<Integer, CartItem> item) {
        this.item = item;
    }
}
