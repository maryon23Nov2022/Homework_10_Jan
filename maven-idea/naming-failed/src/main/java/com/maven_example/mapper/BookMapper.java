package com.maven_example.mapper;

import com.maven_example.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper{
    Book selectByBookName(String bookName);
    void insert(Book book);
    Book selectByIdentification(Book book);
    List<Book> search(@Param("keyword")String keyword, @Param("classification")String classification, @Param("type")String type);
    Book selectById(Integer id);
    Integer updateById(Book book);
}
