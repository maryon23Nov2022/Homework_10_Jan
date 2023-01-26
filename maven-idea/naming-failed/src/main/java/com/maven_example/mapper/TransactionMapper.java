package com.maven_example.mapper;

import com.maven_example.pojo.Transaction;
import org.apache.ibatis.annotations.Param;

public interface TransactionMapper{
    void borrowBook(Transaction transaction);
    Transaction filter(@Param("bookId") Integer bookId, @Param("userId") Integer userId);
}
