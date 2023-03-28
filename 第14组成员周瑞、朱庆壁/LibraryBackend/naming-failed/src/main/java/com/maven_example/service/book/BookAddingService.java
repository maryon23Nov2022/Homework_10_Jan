package com.maven_example.service.book;

import com.alibaba.fastjson.JSON;
import com.maven_example.mapper.BookMapper;
import com.maven_example.pojo.Book;
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

@WebServlet("/addBook")
public class BookAddingService extends HttpServlet{
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
        Object identity = httpSession.getAttribute("identity");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        if(identity == null || !"administrator".equals(identity.toString())){
            printWriter.write("权限错误！");
            return;
        }
        req.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        Book book = JSON.parseObject(line, Book.class);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        Book bookInDB = bookMapper.selectByIdentification(book);
        if(bookInDB == null){
            bookMapper.insert(book);
            sqlSession.commit();
            printWriter.write("提交成功！");
        } else{
            printWriter.write("该书以存在，如想更改图书数量请点击修改按钮。");
        }
        sqlSession.close();
    }
}
