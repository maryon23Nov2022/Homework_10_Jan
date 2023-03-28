package com.maven_example.mapper;

import com.maven_example.pojo.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TransactionMapper{
    void borrowBook(Transaction transaction);
    Transaction filter(@Param("bookId")Integer bookId, @Param("userId")Integer userId);
    List<Transaction> getTransactionById(@Param("userId")Integer userId);
    List<Transaction> getUnreturned(@Param("userId")Integer userId);
    void returnBook(@Param("id")Integer transactionId, @Param("returnDate")Date returnDate);
}
