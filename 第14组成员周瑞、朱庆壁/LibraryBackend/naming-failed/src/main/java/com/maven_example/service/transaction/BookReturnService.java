package com.maven_example.service.transaction;

import com.alibaba.fastjson2.JSON;
import com.maven_example.mapper.BookMapper;
import com.maven_example.mapper.TransactionMapper;
import com.maven_example.pojo.Transaction;
import com.maven_example.utils.CUDLock;
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

@WebServlet("/returnBook")
public class BookReturnService extends HttpServlet{
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");     //the header must be true when the request's credentials mode is 'include'.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        HttpSession httpSession = req.getSession();
        Integer userId = (Integer)httpSession.getAttribute("id");
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        Integer bookId = (Integer)JSON.parseObject(line).get("bookId");

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
        Transaction transaction = transactionMapper.filter(bookId, userId);
        transactionMapper.returnBook(transaction.getId(), new Date());

        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        CUDLock.lock();
        try{
            bookMapper.returnBook(bookId);
        } finally{
            sqlSession.commit();
            sqlSession.close();
            resp.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("还书成功");
            CUDLock.unlock();
        }
    }
}
