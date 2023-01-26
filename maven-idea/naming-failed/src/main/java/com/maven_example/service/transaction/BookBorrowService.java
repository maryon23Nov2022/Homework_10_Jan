package com.maven_example.service.transaction;

import com.alibaba.fastjson.JSON;
import com.maven_example.mapper.TransactionMapper;
import com.maven_example.pojo.Transaction;
import com.maven_example.utils.SqlSessionFactoryUtility;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/borrowBook")
public class BookBorrowService extends HttpServlet{
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        HttpSession httpSession = req.getSession();
        Integer userId = (Integer)httpSession.getAttribute("id");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        System.out.printf("%s\n", httpSession.getId());
        if(userId == null) {
            printWriter.write("请先登录"); return;
        }
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        Transaction transaction = JSON.parseObject(line, Transaction.class);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
        Transaction borrowRecord = transactionMapper.filter(transaction.getBookId(), userId);
        if(borrowRecord != null && borrowRecord.getReturnDate() == null){
            printWriter.write("该书籍已借阅"); return;
        }

        transaction.setUserId(userId);
        transaction.setBorrowDate(new Date());
        System.out.printf("%d %d %s\n", transaction.getBookId(), transaction.getUserId(), transaction.getBorrowDate());

        transactionMapper.borrowBook(transaction);
        sqlSession.commit();
        sqlSession.close();
    }
}
