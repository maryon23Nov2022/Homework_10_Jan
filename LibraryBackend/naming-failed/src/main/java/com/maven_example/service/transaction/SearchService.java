package com.maven_example.service.transaction;

import com.alibaba.fastjson2.JSON;
import com.maven_example.mapper.BookMapper;
import com.maven_example.mapper.TransactionMapper;
import com.maven_example.pojo.Transaction;
import com.maven_example.utils.SqlSessionFactoryUtility;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/transaction/search")
public class SearchService extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        Integer userId = (Integer)httpSession.getAttribute("id");
        if(userId == null){
            printWriter.write("用户未登录"); return;
        }
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
        List<Transaction> list = transactionMapper.getUnreturned(userId);
        List<Integer> bookIdList = new ArrayList<>();
        List<Date> dateList = new ArrayList<>();
        List<String> bookNameList = new ArrayList<>();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        for(Transaction transaction : list) {
            Integer bookId = transaction.getBookId();
            bookIdList.add(bookId);
            dateList.add(transaction.getBorrowDate());
            String bookName = bookMapper.selectById(bookId).getBookName();
            bookNameList.add(bookName);
        }
        Map<String, String> data = new HashMap<>();
        data.put("bookIdList", JSON.toJSONString(bookIdList));
        data.put("dateList", JSON.toJSONString(dateList));
        System.out.printf("%s\n", JSON.toJSONString(dateList));
        data.put("bookNameList", JSON.toJSONString(bookNameList));
        printWriter.write(JSON.toJSONString(data));
        sqlSession.close();
    }
}
