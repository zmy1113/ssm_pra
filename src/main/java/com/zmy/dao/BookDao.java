package com.zmy.dao;

import com.zmy.pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {

    /**
     * 查询所有图书信息
     * @return 返回一个list集合
     */
    @Select("select * from t_book")
    List<Book> queryBookList();

    @Select("SELECT *FROM t_book WHERE price BETWEEN #{startPrice} AND #{endPrice} ")
    List<Book> queryBookByPrice(@Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice);

    @Update("INSERT INTO t_book (NAME,author,price,sales,stock,img_path) " +
            "VALUES(#{book.name},#{book.author},#{book.price},#{book.sales},#{book.stock},'static/img/default.jpg')")
    void addBook(@Param("book") Book book);

    @Select("select * from t_book where id=#{id}")
    Book queryBookById(Integer id);

    @Update("update t_book set name=#{book.name},author=#{book.author},price=#{book.price}," +
            "sales=#{book.sales},stock=#{book.stock} where id=#{id}")
    void updateBook(@Param("book") Book book, @Param("id") Integer id);

    @Delete("delete from t_book where id=#{id}")
    void deleteBookById(Integer id);
}
