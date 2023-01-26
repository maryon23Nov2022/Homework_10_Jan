package com.maven_example.service.book;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maven_example.mapper.BookMapper;
import com.maven_example.mapper.TransactionMapper;
import com.maven_example.pojo.Book;
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
import java.util.List;

@WebServlet("/search")
public class SearchService extends HttpServlet{
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp){
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Headers", "content-type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:4040");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String line = bufferedReader.readLine();
        JSONObject jsonObject = JSON.parseObject(line);
        String keyword = '%' + jsonObject.get("keyword").toString() + '%';
        String classification = '%' + jsonObject.get("classification").toString() + '%';
        String type = '%' + jsonObject.get("type").toString() + '%';
        Character operation = jsonObject.get("operation").toString().charAt(0);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtility.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        List<Book> list = bookMapper.search(keyword, classification, type);
        System.out.printf("%c\n", operation);
        HttpSession httpSession = req.getSession();
        Integer userId = (Integer)httpSession.getAttribute("id");
        if(operation == '1' && userId != null){
            for(int i = 0; i < list.size(); ++ i){
                Integer bookId = list.get(i).getId();
                TransactionMapper transactionMapper = sqlSession.getMapper(TransactionMapper.class);
                Transaction transaction = transactionMapper.filter(bookId, userId);
                if(transaction != null && transaction.getReturnDate() == null) list.remove(i);
            }
        }
        printWriter.write(JSON.toJSONString(list));
    }
}
